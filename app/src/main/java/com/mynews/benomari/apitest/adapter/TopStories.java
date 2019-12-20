package com.mynews.benomari.apitest.adapter;


import android.support.annotation.Nullable;

import com.mynews.benomari.apitest.model.Model.Article;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TopStories {

    @GET("topstories/v2/{section}.{response-format}")
    Observable<Article> getAPI(@Path("section") String sections, @Path("response-format") String format, @Query("api-key") String apiKey);

    @GET("mostpopular/v2/mostviewed/{section}/{time-period}")
    Observable<Article> getMostPopular(@Path("section") String version,@Path("time-period") String sections,@Query("api-key") String apiKey);

    @GET("search/v2/articlesearch.json?sort=newest&")
    Observable<Article> getSearchResult(@Query("q") String toSearch, @Nullable @Query("fq") List<String> filterQuery,@Nullable @Query("begin_date") String beginDate, @Nullable @Query("end_date") String endDate,@Query("api-key") String apiKey);


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

}
