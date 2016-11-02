package com.devdustin.logit.utils;

import android.database.Cursor;

import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class CursorUtils {
    public static Cursor getMockCursor() {
        final Cursor mock = Mockito.mock(Cursor.class);
        when(mock.getColumnIndexOrThrow("text")).thenReturn(0);
        when(mock.getColumnIndexOrThrow("created")).thenReturn(1);
        when(mock.getColumnIndexOrThrow("tag")).thenReturn(2);
        final String theText = "the text";
        when(mock.getString(0)).thenReturn(theText);
        final String created = "10-28-2016 12:10:00.100";
        when(mock.getString(1)).thenReturn(created);
        final String tag = "tag";
        when(mock.getString(2)).thenReturn(tag);

        return mock;
    }
}
