package com.devdustin.logit.view.logmessage.details;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.devdustin.logit.pojo.LogMessage;
import com.devdustin.logit.view.logmessage.thumb.LogThumbView;
import com.devdustin.logit.view.logmessage.thumb.LogThumbViewImpl;

class LogDetailsListViewListAdapter extends CursorAdapter {

    LogDetailsListViewListAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final LogThumbView logThumbView = new LogThumbViewImpl(LayoutInflater.from(context), parent);
        final View newView = logThumbView.getRootView();
        newView.setTag(logThumbView);

        return newView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        final LogMessage logMessage = new LogMessage(cursor);

        ((LogThumbView)view.getTag()).showThumb(logMessage);
    }
}
