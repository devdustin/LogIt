package com.devdustin.logit.database.repository;

import android.content.ContentValues;

public interface LogItDbLoader {
    void executeDelete(String whereClause, String[] whereArgs);
    void insertValues(ContentValues values);
}
