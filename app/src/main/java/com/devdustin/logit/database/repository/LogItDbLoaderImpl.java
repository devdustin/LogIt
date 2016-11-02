package com.devdustin.logit.database.repository;

import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.devdustin.logit.controller.fragment.LogMessagesFragment;
import com.devdustin.logit.database.sqlite.LogMessageEntryContract;
import com.devdustin.logit.database.sqlite.LogMessageEntryDbHelper;

// todo: refactor as ContentProvider, that way we can know when data automatically updates
public class LogItDbLoaderImpl extends CursorLoader implements LogItDbLoader {

    public LogItDbLoaderImpl(Context context) {
        super(context);
    }

    public LogItDbLoaderImpl(Context context, Uri uri, String[] projection, String selection,
                             String[] selectionArgs, String sortOrder) {
        super(context, uri, projection, selection, selectionArgs, sortOrder);
    }

    @Override
    public Cursor loadInBackground() {
        final SQLiteDatabase readableDatabase = getReadableDatabase();

        final String groupBy = null;
        final String having = null;
        final String limit = null;
        return readableDatabase.query(LogMessageEntryContract.TABLE_NAME, getProjection(), getSelection(), getSelectionArgs(), groupBy, having, getSortOrder(), limit);
    }

    @Override
    public void executeDelete(String whereClause, String[] whereArgs) {
        final SQLiteDatabase writeableDatabase = getWriteableDatabase();

        try {
            writeableDatabase.delete(LogMessageEntryContract.TABLE_NAME, whereClause, whereArgs);
        } finally {
            writeableDatabase.close();
        }
    }

    @Override
    public void insertValues(ContentValues values) {
        final SQLiteDatabase writeableDatabase = getWriteableDatabase();
        try {
            long newRowId = writeableDatabase.insert(LogMessageEntryContract.TABLE_NAME, null, values);
            Log.i(LogMessagesFragment.class.getSimpleName(), String.format("Inserted new row at id %d", newRowId));
        } finally {
            writeableDatabase.close();
        }
    }

    private SQLiteDatabase getReadableDatabase() {
        final LogMessageEntryDbHelper dbHelper = new LogMessageEntryDbHelper(getContext());

        return dbHelper.getReadableDatabase();
    }

    private SQLiteDatabase getWriteableDatabase() {
        final LogMessageEntryDbHelper dbHelper = new LogMessageEntryDbHelper(getContext());

        return dbHelper.getWritableDatabase();
    }
}
