package com.devdustin.logit.view.logmessage.detail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.devdustin.logit.R;
import com.devdustin.logit.pojo.LogMessage;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class LogDetailViewImpl implements LogDetailView {

    private final View rootView;
    private final TextView txtLogText;
    private final TextView txtLogCreated;
    private final TextView txtLogTag;
    private final Button btnLogDelete;
    private final java.text.DateFormat dateFormat;
    private final java.text.DateFormat timeFormat;

    private LogDetailsViewListener listener;

    public LogDetailViewImpl(final LayoutInflater inflater, final ViewGroup container) {
        rootView = inflater.inflate(R.layout.logit_view_log_detail, container, false);

        txtLogText = (TextView) rootView.findViewById(R.id.txt_log_text);
        txtLogCreated = (TextView) rootView.findViewById(R.id.txt_log_created);
        txtLogTag = (TextView) rootView.findViewById(R.id.txt_log_tag);
        btnLogDelete = (Button) rootView.findViewById(R.id.btn_log_delete);

        btnLogDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onDeleteClick();
                }
            }
        });

        dateFormat = android.text.format.DateFormat.getMediumDateFormat(inflater.getContext());
        timeFormat = android.text.format.DateFormat.getTimeFormat(inflater.getContext());
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public Bundle getState() {
        final Bundle state = new Bundle();
        state.putString(LOG_CREATED, txtLogCreated.getText().toString());
        state.putString(LOG_TEXT, txtLogText.getText().toString());
        state.putString(LOG_TAG, txtLogTag.getText().toString());
        state.putBoolean(DELETE_VISIBLE, btnLogDelete.getVisibility() == View.VISIBLE);
        return state;
    }

    @Override
    public void hideDeleteButton() {
        btnLogDelete.setVisibility(View.GONE);
    }

    @Override
    public void showDeleteButton() {
        btnLogDelete.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLogDetails(LogMessage logMessage) {
        txtLogText.setText(logMessage.getText());

        String dateString = logMessage.getCreated();
        // attempt to convert to user's locale
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            final Date date;
            try {
                date = sdf.parse(logMessage.getCreated());
                dateString = String.format("%s %s", dateFormat.format(date), timeFormat.format(date));
            } catch (ParseException e) {
                Log.e(LogDetailViewImpl.class.getSimpleName(), String.format("Couldn't parse string to date '%s'", logMessage.getCreated()));
            }
        }
        txtLogCreated.setText(dateString);
        txtLogTag.setText(logMessage.getTag());

        // handle showing the delete button or not, could be based on user permissions, etc
    }

    @Override
    public void setListener(LogDetailsViewListener listener) {
        this.listener = listener;
    }
}
