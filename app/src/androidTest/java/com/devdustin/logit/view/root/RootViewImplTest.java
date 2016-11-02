package com.devdustin.logit.view.root;

import android.content.Context;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.devdustin.logit.view.root.RootViewImpl.NEW_LOG_KEY;
import static com.devdustin.logit.view.root.RootViewImpl.SUBMIT_VISIBLE;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class RootViewImplTest {
    private RootViewImpl unit;

    @Before
    public void setUp() {
        final Context context = InstrumentationRegistry.getTargetContext();
        unit = new RootViewImpl(context, null);
    }

    @Test
    public void getRootView_shouldReturn_theRootView() {
        // arrange
        // act
        final View rootView = unit.getRootView();

        // assert
        assertThat(rootView, is(notNullValue()));
    }

    @Test
    public void getState_shouldReturn_allStateValues() {
        // arrange
        final int numStateEntries = 2;

        // act
        final Bundle state = unit.getState();

        // assert
        assertThat(state, is(Matchers.notNullValue()));
        assertThat(state.size(), is(numStateEntries));
        assertThat(state.containsKey(NEW_LOG_KEY), is(true));
        assertThat(state.containsKey(SUBMIT_VISIBLE), is(true));
    }
}
