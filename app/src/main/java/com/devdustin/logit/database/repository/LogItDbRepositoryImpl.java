package com.devdustin.logit.database.repository;

import android.app.Activity;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.net.Uri;
import android.util.Log;

import com.devdustin.logit.database.repository.factory.CursorLoaderFactory;
import com.devdustin.logit.database.repository.factory.CursorLoaderFactoryImpl;
import com.devdustin.logit.database.repository.factory.LogItDbRepositoryFactory;
import com.devdustin.logit.database.repository.factory.LogItDbRepositoryFactoryImpl;
import com.devdustin.logit.database.sqlite.LogMessageEntryContract;
import com.devdustin.logit.event.LogMessageDeletedEvent;
import com.devdustin.logit.event.LogMessageSubmittedEvent;

import org.greenrobot.eventbus.EventBus;

public class LogItDbRepositoryImpl implements LogItDbRepository {

    private static final String[] ALL_FIELDS_PROJECTION = new String[] {
            LogMessageEntryContract._ID,
            LogMessageEntryContract.COLUMN_NAME_CREATED,
            LogMessageEntryContract.COLUMN_NAME_TEXT,
            LogMessageEntryContract.COLUMN_NAME_TAG
    };
    private final Activity activity;
    private final LogItDbLoader dbLoader;
    private final LogItDbRepositoryFactory dbRepoFactory;
    private final CursorLoaderFactory cursorLoaderFactory;

    public LogItDbRepositoryImpl(Activity activity,
                                 LogItDbRepositoryFactory dbRepoFactory,
                                 CursorLoaderFactory cursorLoaderFactory) {
        this.activity = activity;
        if (dbRepoFactory == null) {
            this.dbRepoFactory = new LogItDbRepositoryFactoryImpl();
        } else {
            this.dbRepoFactory = dbRepoFactory;
        }
        if (cursorLoaderFactory == null) {
            this.cursorLoaderFactory = new CursorLoaderFactoryImpl();
        } else {
            this.cursorLoaderFactory = cursorLoaderFactory;
        }

        dbLoader = this.dbRepoFactory.getLogItDbLoader(this.activity);
    }

    @Override
    public CursorLoader getAllLogMessagesCursorLoader() {
        final Uri contentUri = null;
        final String selection = null;
        final String[] selectionArgs = null;
        final String sortOrder = String.format("%s DESC", LogMessageEntryContract.COLUMN_NAME_CREATED);

        return cursorLoaderFactory.getLogItDbLoaderAsCursorLoader(this.activity, contentUri, ALL_FIELDS_PROJECTION, selection, selectionArgs, sortOrder);
    }

    @Override
    public CursorLoader getLogMessageByIdCursorLoader(long id) {
        final Uri contentUri = null;
        final String selection = String.format("%s = ?", LogMessageEntryContract._ID);
        final String[] selectionArgs = { String.valueOf(id) };
        final String sortOrder = String.format("%s DESC", LogMessageEntryContract.COLUMN_NAME_CREATED);

        return cursorLoaderFactory.getLogItDbLoaderAsCursorLoader(this.activity, contentUri, ALL_FIELDS_PROJECTION, selection, selectionArgs, sortOrder);
    }

    @Override
    public void deleteLogMessageById(long id) {
        Log.i(LogItDbRepositoryImpl.class.getSimpleName(), String.format("Deleting log message with id %d", id));
        final String whereClause = String.format("%s = ?", LogMessageEntryContract._ID);
        final String[] whereArgs = { String.valueOf(id) };
        dbLoader.executeDelete(whereClause, whereArgs);
        EventBus.getDefault().post(new LogMessageDeletedEvent());
    }

    @Override
    public void submitLogMessage(String message) {
        Log.i(LogItDbRepositoryImpl.class.getSimpleName(), "Submitting new log message");

        final ContentValues values = new ContentValues();
        values.put(LogMessageEntryContract.COLUMN_NAME_TEXT, message);
        values.put(LogMessageEntryContract.COLUMN_NAME_TAG, "v1");

        dbLoader.insertValues(values);
        EventBus.getDefault().post(new LogMessageSubmittedEvent());
    }
}
