package com.mynews.benomari.apitest.model.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Multimedium {

    public Multimedium(String url) {
        this.url = url;
    }

    @SerializedName("url")
    @Expose
    private String url;

    public String getUrl() {
        return url;
    }





}