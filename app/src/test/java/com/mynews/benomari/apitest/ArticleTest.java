package com.mynews.benomari.apitest;

import com.mynews.benomari.apitest.model.Model.Multimedium;
import com.mynews.benomari.apitest.model.Model.Result;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArticleTest {
    @Test
    public void getResult() throws Exception{

        //Test for an article
        Result result = new Result("Section","SubSection","Title","ArticleUrl","PublishedDate");
        assertEquals("Section", result.getSection());
        assertEquals("SubSection", result.getSubsection());
        assertEquals("Title", result.getTitles());
        assertEquals("ArticleUrl", result.getUrl());
        assertEquals("PublishedDate", result.getPublishedDate());

        //Test the multimedia url
        Multimedium multimedium = new Multimedium("url");
        assertEquals("url",multimedium.getUrl());

        }
}
