package com.devdustin.logit.view.home;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.devdustin.logit.R;

public class HomeViewImpl implements HomeView {

    private final View rootView;
    private final ListView listView;
    private final HomeListAdapter listAdapter;
    private HomeViewListener listener;

    public HomeViewImpl(LayoutInflater inflater, ViewGroup container) {
        rootView = inflater.inflate(R.layout.logit_view_home, container, false);

        listView = (ListView) rootView.findViewById(R.id.list_log_messages);
        listAdapter = new HomeListAdapter(rootView.getContext(), null, 0);
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
    public void swapCursor(Cursor cursor) {
        listAdapter.swapCursor(cursor);
    }

    @Override
    public void setListener(HomeViewListener listener) {
        this.listener = listener;
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public Bundle getState() {
        return null;
    }
}
