package com.devdustin.logit.view.logmessage.detail;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.view.LayoutInflater;
import android.view.View;

import com.devdustin.logit.pojo.LogMessage;
import com.devdustin.logit.utils.CursorUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(AndroidJUnit4.class)
public class LogDetailViewImplTest {

    private LogDetailViewImpl unit;

    @Before
    public void setUp() {
        final Context context = InstrumentationRegistry.getTargetContext();
        unit = new LogDetailViewImpl(LayoutInflater.from(context), null);
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
        final int numStateEntries = 4;

        // act
        final Bundle state = unit.getState();

        // assert
        assertThat(state, is(notNullValue()));
        assertThat(state.size(), is(numStateEntries));
    }

    @Test
    public void hideDeleteButton_shouldSucceed_byHidingButton() {
        // arrange
        // act
        unit.hideDeleteButton();

        // assert
        final Bundle state = unit.getState();
        assertThat(state, is(notNullValue()));
        assertThat(state.getBoolean(LogDetailViewImpl.DELETE_VISIBLE), is(false));
    }

    @Test
    public void showDeleteButton_shouldSucceed_byHidingButton() {
        // arrange
        // act
        unit.showDeleteButton();

        // assert
        final Bundle state = unit.getState();
        assertThat(state, is(notNullValue()));
        assertThat(state.getBoolean(LogDetailViewImpl.DELETE_VISIBLE), is(true));
    }

    @Test
    public void showLogDetails_shouldSucceed_whenValid() {
        // arrange
        final Cursor mock = CursorUtils.getMockCursor();

        // act
        unit.showLogDetails(new LogMessage(mock));

        // assert
        final Bundle state = unit.getState();
        assertThat(state, is(notNullValue()));
        assertThat(state.getString(LogDetailViewImpl.LOG_TAG), is("tag"));
        assertThat(state.getString(LogDetailViewImpl.LOG_TEXT), is("the text"));
        final String expectedCreated = "Oct 7, 17 5:10 AM";
        assertThat(state.getString(LogDetailViewImpl.LOG_CREATED), is(expectedCreated));
    }
}
