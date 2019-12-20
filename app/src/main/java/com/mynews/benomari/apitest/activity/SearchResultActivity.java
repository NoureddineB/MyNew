package com.mynews.benomari.apitest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.mynews.benomari.apitest.R;
import com.mynews.benomari.apitest.Utils.ApiStreams;
import com.mynews.benomari.apitest.Utils.ItemClickSupport;
import com.mynews.benomari.apitest.adapter.ApiAdapter;
import com.mynews.benomari.apitest.fragment.MainFragment;
import com.mynews.benomari.apitest.model.Model.Article;
import com.mynews.benomari.apitest.model.Model.Result;
import com.mynews.benomari.apitest.model.Model.SearchModel.Docs;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static com.mynews.benomari.apitest.activity.SearchActivity.CHECKBOXES;
import static com.mynews.benomari.apitest.activity.SearchActivity.QUERY_TERM;

public class SearchResultActivity extends AppCompatActivity {

    @BindView(R.id.activity_search_result_recycler_view) RecyclerView recyclerView;

    private Disposable disposable;
    private List articles;
    private ApiAdapter adapter;
    String queryTerm;
    List<String> checkBoxes;
    String beginDate;
    String endDate;

    private final static String API_KEY = "c19f4ae92b4b4a448fa1fa0be4a5971e";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);
        configureToolbar();
        configureRecyclerView();
        configureOnClickRecyclerView();
        getResults();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    // -----------------
    // CONFIGURATION
    // -----------------
    private void configureToolbar() {
        // Get the toolbar view inside the activity layout
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        // Set the Toolbar
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // Configure RecyclerView, Adapter, LayoutManager & glue it together
    private void configureRecyclerView(){
        // 3.1 - Reset list
        this.articles = new ArrayList<>();
        // 3.2 - Create adapter passing the list of users
        this.adapter = new ApiAdapter(this.articles, Glide.with(this));
        // 3.3 - Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.adapter);
        // 3.4 - Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }



    // Configure item click on RecyclerView
    private void configureOnClickRecyclerView(){

        ItemClickSupport.addTo(recyclerView, R.layout.framgent_main_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Docs url = adapter.getUrlFromSearch(position);
                        openDetailActivity(url.getWebUrl());

                    }

                });

    }

    private void openDetailActivity(String url){
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(MainFragment.URL,url);
        startActivity(intent);
    }





    private void getResults(){
        Intent intent = getIntent();
        intent.getBooleanExtra("SEARCH",false);
        queryTerm = intent.getStringExtra(SearchActivity.QUERY_TERM);
        checkBoxes = intent.getStringArrayListExtra(SearchActivity.CHECKBOXES);
        beginDate = intent.getStringExtra(SearchActivity.BEGIN_DATE);
        endDate = intent.getStringExtra(SearchActivity.END_DATE);

        this.disposable = ApiStreams.getSearchResult(queryTerm,checkBoxes,beginDate,endDate,API_KEY).subscribeWith(searchObserver());
    }

    private <T> DisposableObserver<T> searchObserver(){
        return new DisposableObserver<T>() {
            @Override
            public void onNext(T t) {
                Log.e("TAGSEARCHVALUE2", String.valueOf(t));
                if (t instanceof Article){
                    updateUI(((Article) t));

                }
            }
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onComplete() {
                Log.e("TAGSEARCHCOMPLETE", "On Complete !!");
                Log.e("TAGSEARCHVALUE", String.valueOf(checkBoxes));


            }
        };
    }



    private void disposeWhenDestroy(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }


    private void updateUI(Article result){
        articles.clear();
        if (result.getResponse().getDocs().isEmpty()){
            Toast.makeText(this, "No result found !", Toast.LENGTH_SHORT).show();
        }else{
            articles.addAll(result.getResponse().getDocs());
            articles.size();

        }
        adapter.notifyDataSetChanged();
    }
}