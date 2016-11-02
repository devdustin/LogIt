package com.devdustin.logit.database.repository;

import android.content.CursorLoader;

public interface LogItDbRepository {
    CursorLoader getAllLogMessagesCursorLoader();
    CursorLoader getLogMessageByIdCursorLoader(long id);
    void deleteLogMessageById(long id);
    void submitLogMessage(String message);
}
