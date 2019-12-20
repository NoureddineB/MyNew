package com.mynews.benomari.apitest.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mynews.benomari.apitest.R;
import com.mynews.benomari.apitest.fragment.MainFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {


    @BindView(R.id.activity_detail_webview) WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        this.displayWebview();
    }

    //Display Webview
    private void displayWebview(){
        String url = getIntent().getStringExtra(MainFragment.URL);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new WebViewClient());

    }
}