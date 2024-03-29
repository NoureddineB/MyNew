package com.mynews.benomari.apitest.model.Model.SearchModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Docs {
    @SerializedName("web_url")
    @Expose
    private String webUrl;
    @SerializedName("headline")
    @Expose
    private Headline headline;
    @SerializedName("pub_date")
    @Expose
    private String pubDate;
    @SerializedName("section_name")
    @Expose
    private String sectionName;
    @SerializedName("subsection_name")
    @Expose
    private String subsectionName;
    @SerializedName("multimedia")
    @Expose
    private final List<ResponseMultimedia> multimedia = null;


    public Docs(String webUrl, Headline headline, String pubDate, String sectionName, String subsectionName) {
        this.webUrl = webUrl;
        this.headline = headline;
        this.pubDate = pubDate;
        this.sectionName = sectionName;
        this.subsectionName = subsectionName;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public Headline getHeadline() {
        return headline;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getSubsectionName() {
        return subsectionName;
    }

    public List<ResponseMultimedia> getMultimedia() {
        return multimedia;
    }
}