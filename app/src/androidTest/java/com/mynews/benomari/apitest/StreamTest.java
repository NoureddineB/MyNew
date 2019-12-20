package com.mynews.benomari.apitest;


import android.support.test.runner.AndroidJUnit4;

import com.mynews.benomari.apitest.Utils.ApiStreams;
import com.mynews.benomari.apitest.model.Model.Article;
import com.mynews.benomari.apitest.model.Model.Result;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)
public class StreamTest {


    @Test
    public void getArticle() throws Exception {
        //1 - Get the stream
        Observable<Article> observableArticle = ApiStreams.getArticle("home","json","c19f4ae92b4b4a448fa1fa0be4a5971e");
        //2 - Create a new TestObserver
        TestObserver<Article> testObserver = new TestObserver<>();
        //3 - Launch observable
        observableArticle.subscribeWith(testObserver)
                .assertNoErrors() // 3.1 - Check if no errors
                .assertNoTimeout() // 3.2 - Check if no Timeout
                .awaitTerminalEvent(); // 3.3 - Await the stream terminated before continue
        List<Result> articles = testObserver.values().get(0).getResults();
        assertThat("API do return something",articles.get(0) != null);

    }

}

