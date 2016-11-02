package com.devdustin.logit.controller.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.LayoutInflater;
import android.view.View;

import com.devdustin.logit.controller.activity.MainActivity;
import com.devdustin.logit.utils.CursorUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class LogMessagesFragmentTest {

    private LogMessagesFragment unit;

    @Rule
    public final ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class, false, false);

    @Before
    public void setUp() {
        activityTestRule.launchActivity(new Intent());

        final MainActivity activity = activityTestRule.getActivity();
        activity.replaceFragment(LogMessagesFragment.class, false, new Bundle(), false);

        unit = (LogMessagesFragment) activityTestRule.getActivity().getFragmentManager().findFragmentByTag(LogMessagesFragment.class.getSimpleName());
    }

    @Test
    public void onCreateView_shouldCreateView_whenValid() {
        // arrange
        final Context context = InstrumentationRegistry.getTargetContext();

        // act
        final View view = unit.onCreateView(LayoutInflater.from(context), null, null);

        // assert
        assertThat(view, is(notNullValue()));
    }

    @Test
    public void onStart_shouldSucceed_whenValid() {
        // arrange
        // act
        unit.onStart();
        // assert
    }

    @Test
    public void onCreateLoader_shouldSucceed_whenValid() throws Throwable {
        // arrange
        // act
        activityTestRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Loader<Cursor> cursorLoader = unit.onCreateLoader(0, new Bundle());

                // assert
                assertThat(cursorLoader, is(notNullValue()));
            }
        });
    }

    @Test
    public void onLoadFinished_shouldSucceed_whenValid() throws Throwable {
        // arrange
        final Loader<Cursor> loader = new Loader<>(InstrumentationRegistry.getTargetContext());
        final Cursor data = CursorUtils.getMockCursor();

        // act
        activityTestRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                unit.onLoadFinished(loader, data);
                // assert
            }
        });
    }

    @Test
    public void onLoaderReset_shouldSucceed_whenValid() throws Throwable {
        // arrange
        final Loader<Cursor> loader = new Loader<>(InstrumentationRegistry.getTargetContext());

        // act
        activityTestRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                unit.onLoaderReset(loader);
                // assert
            }
        });
    }

    @Test
    public void onListItemClick_shouldReplaceFragment_withDetailsFragment() {
        // arrange
        final int position = 123;
        final int id = 456;

        // act
        unit.onListItemClick(position, id);

        // assert
        // todo: verify that fragment with the above details was swapped
    }
}
