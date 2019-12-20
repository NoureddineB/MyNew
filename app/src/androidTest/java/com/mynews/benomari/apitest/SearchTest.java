package com.mynews.benomari.apitest;

import android.support.test.rule.ActivityTestRule;


import com.mynews.benomari.apitest.activity.SearchActivity;


import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


public class SearchTest {
    @Rule
    public ActivityTestRule<SearchActivity> activityActivityTestRule = new ActivityTestRule<>(SearchActivity.class);


    @Test
    public void testUI() throws Exception {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));

    }
}
