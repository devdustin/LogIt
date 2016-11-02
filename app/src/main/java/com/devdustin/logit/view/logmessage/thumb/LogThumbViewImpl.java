package com.devdustin.logit.view.logmessage.thumb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devdustin.logit.R;
import com.devdustin.logit.pojo.LogMessage;

public class LogThumbViewImpl implements LogThumbView {

    private final View rootView;
    private final TextView txtText;

    public LogThumbViewImpl(final LayoutInflater inflater, final ViewGroup container) {
        rootView = inflater.inflate(R.layout.logit_view_log_thumb, container, false);
        txtText = (TextView) rootView.findViewById(R.id.log_text);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public Bundle getState() {
        final Bundle state = new Bundle();
        state.putString(LOG_THUMB_TEXT, txtText.getText().toString());
        return state;
    }

    @Override
    public void showThumb(LogMessage logMessage) {
        txtText.setText(logMessage.getText());

        // could change background or do something similar here based on state of logMessage
    }
}
