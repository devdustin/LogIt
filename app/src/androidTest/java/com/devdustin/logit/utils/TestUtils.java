package com.devdustin.logit.utils;

import android.app.BuildConfig;

import java.io.File;

import static junit.framework.Assert.fail;

public class TestUtils {
    private static final String CACHE_DIRECTORY = "/data/data/" + BuildConfig.APPLICATION_ID + "/cache";
    public static final String DEXMAKER_CACHE_PROPERTY = "dexmaker.dexcache";

    public static void enableMockitoOnDevicesAndEmulators() {
        if (System.getProperty(DEXMAKER_CACHE_PROPERTY) == null || System.getProperty(DEXMAKER_CACHE_PROPERTY).isEmpty()) {
            File file = new File(CACHE_DIRECTORY);
            if (!file.exists()) {
                final boolean success = file.mkdirs();
                if (!success) {
                    fail("Unable to create cache directory required for Mockito");
                }
            }

            System.setProperty(DEXMAKER_CACHE_PROPERTY, file.getPath());
        }
    }
}
