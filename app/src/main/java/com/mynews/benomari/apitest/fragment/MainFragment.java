package com.mynews.benomari.apitest.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mynews.benomari.apitest.R;
import com.mynews.benomari.apitest.Utils.ApiStreams;
import com.mynews.benomari.apitest.Utils.ItemClickSupport;
import com.mynews.benomari.apitest.activity.DetailsActivity;
import com.mynews.benomari.apitest.activity.MainActivity;
import com.mynews.benomari.apitest.activity.SearchActivity;
import com.mynews.benomari.apitest.adapter.ApiAdapter;
import com.mynews.benomari.apitest.model.Model.Article;
import com.mynews.benomari.apitest.model.Model.Result;
import com.mynews.benomari.apitest.model.Model.SearchModel.Docs;
import com.mynews.benomari.apitest.model.Model.SearchModel.Response;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;


public class MainFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager mViewPager;
    private Disposable disposable;
    public static final String URL = "URL";
    public static final String IDENTIFIER = "Identifier";
    private int identifierID;
    List<String> checkBoxes;





    @BindView(R.id.fragment_main_recycler_view) RecyclerView recyclerView;


    private List<Result> articles;
    private ApiAdapter adapter;
    private List<Result> mResults;






    private final static String API_KEY = "c19f4ae92b4b4a448fa1fa0be4a5971e";

    public MainFragment(){

    }
    private int getIdentifier(){
        return identifierID = getArguments().getInt(IDENTIFIER, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this,v);
        tabLayout = v.findViewById(R.id.activity_main_tabs);
        mViewPager = v.findViewById(R.id.activity_main_viewpager);
        configureRecyclerView();
        this.configureOnClickRecyclerView();
        getResults();
        return v;

    }

    private void configureOnClickRecyclerView(){

        ItemClickSupport.addTo(recyclerView, R.layout.framgent_main_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Result url = adapter.getUrl(position);
                        openDetailActivity(url.getUrl());

                        }

                });

    }
    private void openDetailActivity(String url){
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra(URL,url);
        startActivity(intent);
    }


    private void configureRecyclerView(){
        // 3.1 - Reset list
        this.articles = new ArrayList<>();
        // 3.2 - Create adapter passing the list of users
        this.adapter = new ApiAdapter(this.articles,Glide.with(this));
        // 3.3 - Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.adapter);
        // 3.4 - Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }





    private void getResults(){
        if (getIdentifier() == MainActivity.FRAGMENT_TOP_STORIES) {
            this.disposable = ApiStreams.getArticle("home","json",API_KEY).subscribeWith(create());
        } else if(getIdentifier() == MainActivity.FRAGMENT_MOST_POPULAR) {
            this.disposable = ApiStreams.getMostPopular("all-sections", "7",API_KEY).subscribeWith(create());
        }else if (getIdentifier() == MainActivity.FRAGMENT_BUSINESS){
            this.disposable = ApiStreams.getBuisness("business","json",API_KEY).subscribeWith(create());
        }
        }



   public DisposableObserver<Article> create(){
    return new DisposableObserver<Article>() {
            @Override
            public void onNext(Article article) {
                    mResults = new ArrayList<>();
                    mResults.addAll(article.getResults());
                    updateUI(mResults);





                    }
                @Override
            public void onError(Throwable e) {
                    Log.e("TAG", "Error" + e);


            }

            @Override
            public void onComplete() {
                Log.e("TAG", "On Complete !!");
                Log.e("TAG", String.valueOf(checkBoxes));



            }
        };
    }











    private void updateUI(List<Result> response){
        articles.addAll(response);
        adapter.notifyDataSetChanged();

    }




    public static MainFragment newInstance(int Identifier) {
        MainFragment mainFragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt(IDENTIFIER, Identifier);
        mainFragment.setArguments(args);
        return mainFragment;
    }





}


