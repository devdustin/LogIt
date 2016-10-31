package com.devdustin.logit.database.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LogMessageEntryDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "LogIt.db";
    private static final int DATABASE_VERSION = 3;

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + LogMessageEntryContract.LogMessageEntry.TABLE_NAME + "( " +
                    LogMessageEntryContract.LogMessageEntry._ID + " INTEGER PRIMARY KEY, " +
                    LogMessageEntryContract.LogMessageEntry.COLUMN_NAME_CREATED + " DATETIME DEFAULT(STRFTIME('%Y-%m-%d %H:%M:%f', 'NOW')), " +
                    LogMessageEntryContract.LogMessageEntry.COLUMN_NAME_TEXT + TEXT_TYPE + COMMA_SEP +
                    LogMessageEntryContract.LogMessageEntry.COLUMN_NAME_TAG + TEXT_TYPE + " )";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + LogMessageEntryContract.LogMessageEntry.TABLE_NAME;

    public LogMessageEntryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // todo: migrate the db instead of just blow it away
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // todo: implement a downgrade path
        onUpgrade(db, oldVersion, newVersion);
    }
}
