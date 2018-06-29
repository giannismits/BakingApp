package com.udacity.giannis.bakingapp.bakindapp;


import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.giannis.bakingapp.bakindapp.ui.BakingMainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class TestApp {

    @Rule
    public ActivityTestRule<BakingMainActivity> bakingMainActivityActivityTestRule =
            new ActivityTestRule<>(BakingMainActivity.class);

    @Test
    public void testRecycleViewer(){
        onView(withRecyclerView(R.id.main_recycle_view).atPositionOnView(0, R.id.recipe_name)).check(matches(withText("Nutella Pie")));

        onView(ViewMatchers.withId(R.id.main_recycle_view)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        onView(withId(R.id.steps_container)).check(matches(isDisplayed()));
        onView(withId(R.id.ingredients_container)).check(matches(isDisplayed()));


        onView(ViewMatchers.withId(R.id.steps_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,
                        click()));
        onView(withId(R.id.ingredients_recycle_view)).check(matches(isDisplayed()));
    }

    public static RecycleViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecycleViewMatcher(recyclerViewId);
    }

}
