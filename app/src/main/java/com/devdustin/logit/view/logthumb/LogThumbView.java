package com.devdustin.logit.view.logthumb;

import com.devdustin.logit.pojo.LogMessage;
import com.devdustin.logit.view.LogItView;

public interface LogThumbView extends LogItView {
    void showThumb(LogMessage logMessage);
}
