package com.devdustin.logit.database.repository.factory;

import android.content.Context;
import android.content.CursorLoader;
import android.net.Uri;

public interface CursorLoaderFactory {
    CursorLoader getLogItDbLoaderAsCursorLoader(Context context, Uri uri, String[] projection, String selection,
                                                String[] selectionArgs, String sortOrder);
}
