package com.devdustin.logit.view.logmessage.thumb;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.devdustin.logit.pojo.LogMessage;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static com.devdustin.logit.view.logmessage.thumb.LogThumbView.LOG_THUMB_TEXT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class LogThumbViewImplTest {

    private LogThumbViewImpl unit;

    @Before
    public void setUp() {
        final Context context = InstrumentationRegistry.getTargetContext();
        unit = new LogThumbViewImpl(context, null);
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
        final int numStateEntries = 1;

        // act
        final Bundle state = unit.getState();

        // assert
        assertThat(state, Matchers.is(notNullValue()));
        assertThat(state.size(), Matchers.is(numStateEntries));
        assertThat(state.containsKey(LOG_THUMB_TEXT), is(true));
    }

    @Test
    public void showThumb_shouldSetText_ontTextView() {
        // arrange
        final Cursor mock = Mockito.mock(Cursor.class);
        when(mock.getColumnIndexOrThrow("text")).thenReturn(0);
        final String theText = "the text";
        when(mock.getString(0)).thenReturn(theText);

        // act
        unit.showThumb(new LogMessage(mock));

        // assert
        final Bundle state = unit.getState();
        assertThat(state, is(notNullValue()));
        final String actualText = state.getString(LOG_THUMB_TEXT);
        assertThat(actualText, is(theText));
    }
}
