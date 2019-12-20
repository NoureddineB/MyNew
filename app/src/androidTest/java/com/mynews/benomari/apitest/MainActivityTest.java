package com.mynews.benomari.apitest;




import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mynews.benomari.apitest.activity.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.core.AllOf.allOf;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

        @Rule
        public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

        @Before
        public void init(){
            activityActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction();
        }

        @Test
        public void testUI() throws Exception {
            onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
            onView(withId(R.id.activity_main_tabs)).check(matches(isDisplayed()));
            onView(withId(R.id.activity_main_viewpager)).check(matches(isDisplayed()));
            onView(allOf(withId(R.id.fragment_main_recycler_view),isDisplayed())).check(matches(isDisplayed()));
        }









}
