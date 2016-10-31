package com.devdustin.logit.view.logthumb;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devdustin.logit.R;
import com.devdustin.logit.pojo.LogMessage;

public class LogThumbViewImpl implements LogThumbView {

    private final Context context;
    private final View rootView;
    private final TextView txtText;

    public LogThumbViewImpl(Context context, ViewGroup container) {
        this.context = context;
        rootView = LayoutInflater.from(context).inflate(R.layout.logit_view_log_thumb, container, false);
        txtText = (TextView) rootView.findViewById(R.id.log_text);
    }

    @Override
    public void showThumb(LogMessage logMessage) {
        txtText.setText(logMessage.getText());

        // could change background or do something similar here based on state of logMessage

        // version specific checks
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            rootView.setBackgroundColor(context.getResources().getColor(android.R.color.holo_blue_light, null));
        }
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
