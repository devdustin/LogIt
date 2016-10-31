package com.devdustin.logit.controller.fragment;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devdustin.logit.pojo.LogMessage;
import com.devdustin.logit.view.logdetail.LogDetailView;
import com.devdustin.logit.view.logdetail.LogDetailViewImpl;

public class LogMessageDetailsFragment extends AbstractFragment implements LogDetailView.LogDetailsViewListener, LoaderManager.LoaderCallbacks<Cursor> {

    public static final String ARG_LOG_MESSAGE_ID = "arg_log_message_id";
    private static final int DB_LOADER = 0;
    private LogDetailView view;
    private long logMessageId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new LogDetailViewImpl(inflater, container);
        view.setListener(this);

        return view.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();

        final Bundle args = getArguments();
        if (args.containsKey(ARG_LOG_MESSAGE_ID)) {
            logMessageId = args.getLong(ARG_LOG_MESSAGE_ID);
            getLoaderManager().initLoader(DB_LOADER, args, this);
        } else {
            // no id supplied, can't do much
            Log.e(LogMessageDetailsFragment.class.getSimpleName(), "no LogMessage id was passed in as an arg");
        }

        // here could show or hide pieces based on permission, etc
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case DB_LOADER:
                return getRepository().getLogMessageById(logMessageId);
            default:
                Log.e(LogMessageDetailsFragment.class.getSimpleName(), String.format("onCreateLoader() called, didn't recognize id: %d", id));
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()) {
            case DB_LOADER :
                if (data.moveToFirst()) {
                    final LogMessage logMessage = new LogMessage(data);

                    // tell the view to update
                    view.showLogDetails(logMessage);
                } else {
                    Log.e(LogMessageDetailsFragment.class.getSimpleName(), "cursor is empty");
                }
                break;
            default:
                Log.e(LogMessageDetailsFragment.class.getSimpleName(), String.format("onLoadFinished() called, didn't recognize id: %d", loader.getId()));
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        switch (loader.getId()) {
            case DB_LOADER :
                // do nothing
                break;
            default:
                Log.e(LogMessageDetailsFragment.class.getSimpleName(), String.format("onLoaderReset() called, didn't recognize id: %d", loader.getId()));
                break;
        }
    }

    @Override
    public void onDeleteClick() {
        Log.i(LogMessageDetailsFragment.class.getSimpleName(), String.format("Deleting id %d", logMessageId));
        getRepository().deleteLogMessageById(logMessageId);
    }
}
