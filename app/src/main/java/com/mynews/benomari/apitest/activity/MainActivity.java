package com.mynews.benomari.apitest.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import com.mynews.benomari.apitest.R;
import com.mynews.benomari.apitest.adapter.PageAdapter;

public class MainActivity extends AppCompatActivity {
    public static final int FRAGMENT_TOP_STORIES = 0;
    public static final int FRAGMENT_MOST_POPULAR = 1;
    public static final int FRAGMENT_BUSINESS = 2;


    ViewPager pager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = findViewById(R.id.activity_main_viewpager);
        configureToolbar();
        configureViewPagerAndTabs();
        showFragment(FRAGMENT_TOP_STORIES);

    }



    private void configureToolbar() {
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_36dp);
        // Set the Toolbar
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("My News");

        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handle actions on menu items
        switch (item.getItemId()) {
            case R.id.menu_activity_main_search:
                launchSearchActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private void launchSearchActivity() {
        Intent myIntent = new Intent(MainActivity.this, SearchActivity.class);
        this.startActivity(myIntent);

    }

    private void configureViewPagerAndTabs() {
        TabLayout tabs = findViewById(R.id.activity_main_tabs);
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        tabs.setupWithViewPager(pager);
        tabs.setTabMode(TabLayout.MODE_FIXED);



    }

    private void showFragment(int fragmentIdentifier) {
        switch (fragmentIdentifier) {
            case FRAGMENT_TOP_STORIES:
                pager.setCurrentItem(FRAGMENT_TOP_STORIES);
                break;
            case FRAGMENT_MOST_POPULAR:
                pager.setCurrentItem(FRAGMENT_MOST_POPULAR);
                break;
            case FRAGMENT_BUSINESS:
                pager.setCurrentItem(FRAGMENT_BUSINESS);
                break;
        }
    }


}




