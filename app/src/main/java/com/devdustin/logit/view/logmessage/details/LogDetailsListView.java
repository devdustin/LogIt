package com.devdustin.logit.view.logmessage.details;

import android.database.Cursor;

import com.devdustin.logit.view.LogItView;

public interface LogDetailsListView extends LogItView {
    interface LogDetailsViewListener {
        void onListItemClick(int position, long id);
    }

    void swapCursor(Cursor cursor);
    void setListener(LogDetailsViewListener listener);
}
