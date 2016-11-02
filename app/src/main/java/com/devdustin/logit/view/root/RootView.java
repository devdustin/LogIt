package com.devdustin.logit.view.root;

import com.devdustin.logit.view.LogItView;

public interface RootView extends LogItView {
    String NEW_LOG_KEY = "newLogMessage";
    String SUBMIT_VISIBLE = "submitVisible";

    interface RootViewListener {
        void onSubmitClick();
    }

    void setListener(RootViewListener listener);
}
