package com.mynews.benomari.apitest.Utils;

import android.support.annotation.Nullable;

import com.mynews.benomari.apitest.adapter.TopStories;
import com.mynews.benomari.apitest.model.Model.Article;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;




public class ApiStreams {



    public static Observable<Article> getArticle(String sections, String format, String apikey) {
        TopStories topStories = TopStories.retrofit.create(TopStories.class);
        return topStories.getAPI(sections, format, apikey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        }

    public static Observable<Article> getMostPopular(String sections,String version,String apikey) {
        TopStories topStories = TopStories.retrofit.create(TopStories.class);
        return topStories.getMostPopular(sections,version,apikey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        }

    public static Observable<Article> getBuisness(String sections,String format,String apikey) {
        TopStories topStories = TopStories.retrofit.create(TopStories.class);
        return topStories.getAPI(sections,format,apikey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<Article> getSearchResult(String query, @Nullable List<String> filter,@Nullable String beginDate,@Nullable String endDate, String apikey) {
        TopStories topStories = TopStories.retrofit.create(TopStories.class);
        return topStories.getSearchResult(query,filter,beginDate,endDate,apikey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
