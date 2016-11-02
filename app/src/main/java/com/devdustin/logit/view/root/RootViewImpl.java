package com.devdustin.logit.view.root;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.devdustin.logit.R;
import com.devdustin.logit.view.LogItView;

public class RootViewImpl implements LogItView {

    public static final String NEW_LOG_KEY = "newLogMessage";
    public static final String SUBMIT_VISIBLE = "submitVisible";

    private final View rootView;
    private final EditText txtNewLog;
    private final Button btnSubmitNew;

    public interface RootViewListener {
        void onSubmitClick();
    }
    private RootViewListener listener;

    public RootViewImpl(final Context context, ViewGroup container) {
        rootView = LayoutInflater.from(context).inflate(R.layout.logit_view_root, container);

        txtNewLog = (EditText) rootView.findViewById(R.id.txt_new_log);
        btnSubmitNew = (Button) rootView.findViewById(R.id.btn_submit_new);

        btnSubmitNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onSubmitClick();
                }
                txtNewLog.setText(null);
                final InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public Bundle getState() {
        final Bundle state = new Bundle(1);
        state.putString(NEW_LOG_KEY, txtNewLog.getText().toString());
        state.putBoolean(SUBMIT_VISIBLE, btnSubmitNew.getVisibility() == View.VISIBLE);
        return state;
    }

    public void setListener(RootViewListener listener) {
        this.listener = listener;
    }
}
