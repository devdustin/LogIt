package com.devdustin.logit.database.repository.factory;

import android.content.Context;
import android.content.CursorLoader;
import android.net.Uri;

import com.devdustin.logit.database.repository.LogItDbLoaderImpl;

public class CursorLoaderFactoryImpl implements CursorLoaderFactory {
    @Override
    public CursorLoader getLogItDbLoaderAsCursorLoader(Context context, Uri uri, String[] projection, String selection,
                                                       String[] selectionArgs, String sortOrder) {
        return new LogItDbLoaderImpl(context, uri, projection, selection, selectionArgs, sortOrder);
    }
}
