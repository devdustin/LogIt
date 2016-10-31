package com.devdustin.logit.database.repository;

import android.app.Activity;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.devdustin.logit.database.sqlite.LogMessageEntryContract;
import com.devdustin.logit.event.LogMessageDeletedEvent;
import com.devdustin.logit.event.LogMessageSubmittedEvent;
import com.devdustin.logit.view.root.RootViewImpl;

import org.greenrobot.eventbus.EventBus;

public class LogItDbRepositoryImpl implements LogItDbRepository {

    private static final String[] ALL_FIELDS_PROJECTION = new String[] {
            LogMessageEntryContract.LogMessageEntry._ID,
            LogMessageEntryContract.LogMessageEntry.COLUMN_NAME_CREATED,
            LogMessageEntryContract.LogMessageEntry.COLUMN_NAME_TEXT,
            LogMessageEntryContract.LogMessageEntry.COLUMN_NAME_TAG
    };
    private final Activity activity;
    private final LogItDbLoader dbLoader;

    public LogItDbRepositoryImpl(Activity activity) {
        this.activity = activity;
        dbLoader = new LogItDbLoaderImpl(activity);
    }

    @Override
    public CursorLoader getAllLogMessages() {
        final Uri contentUri = null;
        final String selection = null;
        final String[] selectionArgs = null;
        final String sortOrder = String.format("%s DESC", LogMessageEntryContract.LogMessageEntry.COLUMN_NAME_CREATED);

        return new LogItDbLoaderImpl(this.activity, contentUri, ALL_FIELDS_PROJECTION, selection, selectionArgs, sortOrder);
    }

    @Override
    public CursorLoader getLogMessageById(long id) {
        final Uri contentUri = null;
        final String selection = String.format("%s = ?", LogMessageEntryContract.LogMessageEntry._ID);
        final String[] selectionArgs = { String.valueOf(id) };
        final String sortOrder = String.format("%s DESC", LogMessageEntryContract.LogMessageEntry.COLUMN_NAME_CREATED);

        return new LogItDbLoaderImpl(this.activity, contentUri, ALL_FIELDS_PROJECTION, selection, selectionArgs, sortOrder);
    }

    @Override
    public void deleteLogMessageById(long id) {
        Log.i(LogItDbRepositoryImpl.class.getSimpleName(), String.format("Deleting log message with id %d", id));
        final String whereClause = String.format("%s = ?", LogMessageEntryContract.LogMessageEntry._ID);
        final String[] whereArgs = { String.valueOf(id) };
        dbLoader.executeDelete(whereClause, whereArgs);
        EventBus.getDefault().post(new LogMessageDeletedEvent());
    }

    @Override
    public void submitEntry(Bundle state) {
        Log.i(LogItDbRepositoryImpl.class.getSimpleName(), "Submitting new log message");
        final String message = state.getString(RootViewImpl.NEW_LOG_KEY);

        final ContentValues values = new ContentValues();
        values.put(LogMessageEntryContract.LogMessageEntry.COLUMN_NAME_TEXT, message);
        values.put(LogMessageEntryContract.LogMessageEntry.COLUMN_NAME_TAG, "v1");

        dbLoader.insertValues(values);
        EventBus.getDefault().post(new LogMessageSubmittedEvent());
    }
}
