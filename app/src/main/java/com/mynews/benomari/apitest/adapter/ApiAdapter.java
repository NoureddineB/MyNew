package com.mynews.benomari.apitest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.mynews.benomari.apitest.R;
import com.mynews.benomari.apitest.activity.ApiViewHolder;
import com.mynews.benomari.apitest.model.Model.Result;
import com.mynews.benomari.apitest.model.Model.SearchModel.Docs;

import java.util.List;

public class ApiAdapter extends RecyclerView.Adapter<ApiViewHolder> {
// FOR DATA

    private List articles;


    private RequestManager glide;


    // CONSTRUCTOR
    public ApiAdapter(List<Result> articles, RequestManager glide) {
        this.articles = articles;
        this.glide = glide;

    }


    @NonNull
    @Override
    public ApiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.framgent_main_item, parent, false);
        return new ApiViewHolder(view);

    }


    @Override
    public void onBindViewHolder(ApiViewHolder viewHolder, int position) {
        viewHolder.updateWithArticle(this.articles.get(position), this.glide);

    }


    @Override
    public int getItemCount() {
        return this.articles.size();

    }

    public Result getUrl(int position) {
        List<Result> result = articles;
        return result.get(position);
    }
    public Docs getUrlFromSearch(int position){
        List<Docs> result = articles;
        return result.get(position);
    }

}

