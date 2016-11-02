package com.devdustin.logit.database.repository.factory;

import android.app.Activity;

import com.devdustin.logit.database.repository.LogItDbLoader;

public interface LogItDbRepositoryFactory {
    LogItDbLoader getLogItDbLoader(Activity activity);
}
