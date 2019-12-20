package com.mynews.benomari.apitest.model.Model.SearchModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Response {
    @SerializedName("docs")
    @Expose
    private final List<Docs> docs = null;

    public List<Docs> getDocs() {
        return docs;
    }

}

