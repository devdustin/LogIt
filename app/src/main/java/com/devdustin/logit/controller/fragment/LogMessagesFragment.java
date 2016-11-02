package com.devdustin.logit.controller.fragment;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devdustin.logit.view.logmessage.details.LogDetailsView;
import com.devdustin.logit.view.logmessage.details.LogDetailsViewImpl;

public class LogMessagesFragment extends AbstractFragment implements LogDetailsView.LogDetailsViewListener, LoaderManager.LoaderCallbacks<Cursor> {

    private LogDetailsView logDetailsView;
    private static final int DB_LOADER = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // todo: inject this
        logDetailsView = new LogDetailsViewImpl(inflater, container);
        logDetailsView.setListener(this);

        return logDetailsView.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();

        getLoaderManager().initLoader(DB_LOADER, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case DB_LOADER:
                return getRepository().getAllLogMessagesCursorLoader();
            default:
                Log.e(LogMessagesFragment.class.getSimpleName(), String.format("onCreateLoader() called with unrecognized id: %d", id));
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()) {
            case DB_LOADER:
                logDetailsView.swapCursor(data);
                break;
            default:
                Log.e(LogMessagesFragment.class.getSimpleName(), String.format("onLoadFinished() called with unrecognized id: %d", loader.getId()));
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        switch (loader.getId()) {
            case DB_LOADER:
                // release resources
                logDetailsView.swapCursor(null);
                break;
            default:
                Log.e(LogMessagesFragment.class.getSimpleName(), String.format("onLoaderReset() called with unrecognized id: %d", loader.getId()));
                break;
        }
    }

    @Override
    public void onListItemClick(int position, long id) {
        final int argLength = 1;
        final Bundle args = new Bundle(argLength);
        args.putLong(LogMessageDetailsFragment.ARG_LOG_MESSAGE_ID, id);

        final boolean refresh = false;
        replaceFragment(LogMessageDetailsFragment.class, true, args, refresh);
    }
}
