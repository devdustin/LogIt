package com.devdustin.logit.view.logmessage.detail;

import com.devdustin.logit.pojo.LogMessage;
import com.devdustin.logit.view.LogItView;

public interface LogDetailView extends LogItView {
    String LOG_TEXT = "TXT_LOG_TEXT";
    String LOG_CREATED = "TXT_LOG_CREATED";
    String LOG_TAG = "TXT_LOG_TAG";
    String DELETE_VISIBLE = "BTN_DELETE_VISIBILE";

    interface LogDetailsViewListener {
        void onDeleteClick();
    }

    void hideDeleteButton();
    void showDeleteButton();
    void showLogDetails(LogMessage logMessage);
    void setListener(LogDetailsViewListener listener);
}
