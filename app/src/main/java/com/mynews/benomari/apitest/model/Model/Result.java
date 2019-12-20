package com.mynews.benomari.apitest.model.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mynews.benomari.apitest.model.Model.MostPopularModel.Medium;
import com.mynews.benomari.apitest.model.Model.SearchModel.Response;

public class Result {

    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("subsection")
    @Expose
    private String subsection;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("published_date")
    @Expose
    private String publishedDate;
    @SerializedName("multimedia")
    @Expose
    private List<Multimedium> multimedia = null;


    //For Most Popular
    @SerializedName("media")
    @Expose
    private List<Medium> media = null;



    public Result(String section, String subsection, String title, String url, String publishedDate) {
        this.section = section;
        this.subsection = subsection;
        this.title = title;
        this.url = url;
        this.publishedDate = publishedDate;

    }


    public String getSection() {
        return section;
    }


    public String getSubsection() {
        return subsection;
    }

    public String getTitles() {
        return title;
    }

    public String getUrl() {
        return url;
    }




    public String getPublishedDate() {
        return publishedDate;
    }


    public List<Multimedium> getMultimedia() {
        return multimedia;
    }

    //For Most Popular
    public List<Medium> getMedia() {
        return media;
    }



}


