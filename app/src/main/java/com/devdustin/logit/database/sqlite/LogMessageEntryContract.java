package com.devdustin.logit.database.sqlite;

import android.provider.BaseColumns;

public final class LogMessageEntryContract {
    private LogMessageEntryContract() {
    }

    public static class LogMessageEntry implements BaseColumns {
        public static final String TABLE_NAME = "log_message";
        public static final String COLUMN_NAME_TEXT = "text";
        public static final String COLUMN_NAME_CREATED = "created";
        public static final String COLUMN_NAME_TAG = "tag";
    }
}
