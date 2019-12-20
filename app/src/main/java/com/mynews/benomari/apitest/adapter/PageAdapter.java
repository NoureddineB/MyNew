package com.mynews.benomari.apitest.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mynews.benomari.apitest.activity.MainActivity;
import com.mynews.benomari.apitest.fragment.MainFragment;

public class PageAdapter extends FragmentPagerAdapter {

    //Default Constructor
    public PageAdapter(FragmentManager mgr) {
        super(mgr);

    }


    @Override
    public int getCount() {
        return(3);

    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case MainActivity.FRAGMENT_TOP_STORIES: //Page number 1
                return MainFragment.newInstance(MainActivity.FRAGMENT_TOP_STORIES);
            case MainActivity.FRAGMENT_MOST_POPULAR: //Page number 2
                return MainFragment.newInstance(MainActivity.FRAGMENT_MOST_POPULAR);
            case MainActivity.FRAGMENT_BUSINESS: //Page number 3
                return MainFragment.newInstance(MainActivity.FRAGMENT_BUSINESS);
                default:
                return null;
        }

    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: //Page number 1
                return "TOP STORIES";
            case 1: //Page number 2
                return "MOST ";
            case 2: //Page number 3
                return "BUISNESS";
                default:
                    return null;

        }

    }


}
