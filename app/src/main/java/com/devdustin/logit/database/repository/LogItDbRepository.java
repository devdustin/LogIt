package com.devdustin.logit.database.repository;

import android.content.CursorLoader;
import android.os.Bundle;

public interface LogItDbRepository {
    CursorLoader getAllLogMessages();
    CursorLoader getLogMessageById(long id);
    void deleteLogMessageById(long id);
    void submitEntry(Bundle state);
}
