package com.devdustin.logit.view.logmessage.details;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.devdustin.logit.R;

public class LogDetailsListViewImpl implements LogDetailsListView {

    private final View rootView;
    private final ListView listView;
    private final LogDetailsListViewListAdapter listAdapter;
    private LogDetailsViewListener listener;

    public LogDetailsListViewImpl(final LayoutInflater inflater, final ViewGroup container) {
        rootView = inflater.inflate(R.layout.logit_view_log_details_list, container, false);

        listView = (ListView) rootView.findViewById(R.id.list_log_messages);
        listAdapter = new LogDetailsListViewListAdapter(rootView.getContext(), null, 0);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listener != null) {
                    listener.onListItemClick(position, id);
                }
            }
        });
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public Bundle getState() {
        return null;
    }

    @Override
    public void swapCursor(Cursor cursor) {
        listAdapter.swapCursor(cursor);
    }

    @Override
    public void setListener(LogDetailsViewListener listener) {
        this.listener = listener;
    }
}
