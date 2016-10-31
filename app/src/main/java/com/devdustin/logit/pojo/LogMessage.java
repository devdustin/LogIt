package com.devdustin.logit.pojo;

import android.database.Cursor;

public class LogMessage {
    private String text;
    private String created;
    private String tag;

    public LogMessage(Cursor cursor) {
        this.text = cursor.getString(cursor.getColumnIndexOrThrow("text"));
        this.created = cursor.getString(cursor.getColumnIndexOrThrow("created"));
        this.tag = cursor.getString(cursor.getColumnIndexOrThrow("tag"));
    }

    public String getText() {
        return text;
    }

    public String getCreated() {
        return created;
    }

    public String getTag() {
        return tag;
    }
}
