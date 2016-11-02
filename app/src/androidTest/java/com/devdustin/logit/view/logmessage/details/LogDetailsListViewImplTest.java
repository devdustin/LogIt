package com.devdustin.logit.view.logmessage.details;

import android.content.Context;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.view.LayoutInflater;
import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class LogDetailsListViewImplTest {
    private LogDetailsListViewImpl unit;

    @Before
    public void setUp() {
        final Context context = InstrumentationRegistry.getTargetContext();
        unit = new LogDetailsListViewImpl(LayoutInflater.from(context), null);
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
        // act
        final Bundle state = unit.getState();

        // assert
        // this class doesn't hold any state
        assertThat(state, is(nullValue()));
    }
}
