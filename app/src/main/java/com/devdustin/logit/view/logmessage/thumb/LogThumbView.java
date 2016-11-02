package com.devdustin.logit.view.logmessage.thumb;

import com.devdustin.logit.pojo.LogMessage;
import com.devdustin.logit.view.LogItView;

public interface LogThumbView extends LogItView {
    String LOG_THUMB_TEXT = "TXT_LOG_THUMB_TEXT";

    void showThumb(LogMessage logMessage);
}
