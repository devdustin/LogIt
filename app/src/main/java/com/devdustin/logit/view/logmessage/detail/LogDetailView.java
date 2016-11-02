package com.devdustin.logit.view.logmessage.detail;

import com.devdustin.logit.pojo.LogMessage;
import com.devdustin.logit.view.LogItView;

public interface LogDetailView extends LogItView {
    interface LogDetailsViewListener {
        void onDeleteClick();
    }

    void hideDeleteButton();
    void showDeleteButton();
    void showLogDetails(LogMessage logMessage);
    void setListener(LogDetailsViewListener listener);
}
