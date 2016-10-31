package com.devdustin.logit.view.home;

import android.database.Cursor;

import com.devdustin.logit.view.LogItView;

public interface HomeView extends LogItView {
    interface HomeViewListener {
        void onListItemClick(int position, long id);
    }

    void swapCursor(Cursor cursor);
    void setListener(HomeViewListener listener);
}
