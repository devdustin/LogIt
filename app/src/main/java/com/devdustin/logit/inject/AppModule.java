package com.devdustin.logit.inject;

import com.devdustin.logit.database.repository.LogItDbRepository;
import com.devdustin.logit.database.repository.LogItDbRepositoryImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    @Provides static LogItDbRepository provideLogItDbRepository() {
        return new LogItDbRepositoryImpl()
    }
}
