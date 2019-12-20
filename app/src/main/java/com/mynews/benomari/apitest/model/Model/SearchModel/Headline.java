package com.mynews.benomari.apitest.model.Model.SearchModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Headline {
    @SerializedName("main")
    @Expose
    private String main;
    public String getMain() {
        return main;
    }
}