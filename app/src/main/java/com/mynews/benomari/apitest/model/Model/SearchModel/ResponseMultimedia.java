package com.mynews.benomari.apitest.model.Model.SearchModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseMultimedia {
    @SerializedName("url")
    @Expose
    private String url;

    public ResponseMultimedia(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}