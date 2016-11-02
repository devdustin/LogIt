package com.devdustin.logit.database.repository.factory;

import android.app.Activity;

import com.devdustin.logit.database.repository.LogItDbLoader;
import com.devdustin.logit.database.repository.LogItDbLoaderImpl;

public class LogItDbRepositoryFactoryImpl implements LogItDbRepositoryFactory {
    @Override
    public LogItDbLoader getLogItDbLoader(Activity activity) {
        return new LogItDbLoaderImpl(activity);
    }
}
