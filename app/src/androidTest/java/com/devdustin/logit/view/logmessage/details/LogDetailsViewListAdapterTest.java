package com.devdustin.logit.view.logmessage.details;

import android.content.Context;
import android.database.Cursor;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.devdustin.logit.utils.CursorUtils;
import com.devdustin.logit.view.logmessage.thumb.LogThumbView;
import com.devdustin.logit.view.logmessage.thumb.LogThumbViewImpl;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class LogDetailsViewListAdapterTest {
    private LogDetailsViewListAdapter unit;

    @Before
    public void setUp() {
        final Context context = InstrumentationRegistry.getTargetContext();
        unit = new LogDetailsViewListAdapter(context, null, 0);
    }

    @Test
    public void newView_shouldReturn_aTaggedNewView() {
        // arrange
        final Context context = InstrumentationRegistry.getTargetContext();

        // act
        final View view = unit.newView(context, null, null);

        // assert
        assertThat(view, is(notNullValue()));
        final Object tag = view.getTag();
        assertThat(tag, is(notNullValue()));
    }

    @Test
    public void bindView_shouldAttachMessageText_toView() {
        // arrange
        final Context context = InstrumentationRegistry.getTargetContext();
        final View viewToBind = new View(context);
        final LogThumbView thumbView = new LogThumbViewImpl(context, null);
        viewToBind.setTag(thumbView);
        final Cursor mockCursor = CursorUtils.getMockCursor();

        // act
        unit.bindView(viewToBind, context, mockCursor);

        // assert
        assertThat(viewToBind, is(notNullValue()));
        assertThat(viewToBind.getTag(), Matchers.<Object>is(thumbView));
    }
}
