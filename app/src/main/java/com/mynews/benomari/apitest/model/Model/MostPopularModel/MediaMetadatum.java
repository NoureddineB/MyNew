package com.mynews.benomari.apitest.model.Model.MostPopularModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MediaMetadatum {
    @SerializedName("url")
    @Expose
    private String url;

    public String getUrl() {
        return url;
    }


}




