package com.devdustin.logit.database.repository;

import android.app.Activity;
import android.content.Context;
import android.content.CursorLoader;
import android.net.Uri;

import com.devdustin.logit.database.repository.factory.CursorLoaderFactory;
import com.devdustin.logit.database.repository.factory.LogItDbRepositoryFactory;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;

public class LogItDbRepositoryImplTest {
    private LogItDbRepositoryImpl unit;
    @Mock private LogItDbRepositoryFactory dbRepoFactory;
    @Mock private CursorLoaderFactory cursorLoaderFactory;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        final Activity fakeActivity = new Activity();
        unit = new LogItDbRepositoryImpl(fakeActivity, dbRepoFactory, cursorLoaderFactory);
    }

    @Test
    public void getAllLogMessagesCursorLoader_shouldReturn_cursorLoaderForAllMessages() {
        // arrange
        setUpMockCursorLoader();

        // act
        final CursorLoader loader = unit.getAllLogMessagesCursorLoader();

        // assert
        assertThat(loader, is(notNullValue()));
    }

    @Test
    public void getLogMessageByIdCursorLoader_shouldReturn_cursorLoader() {
        // arrange
        final long id = 12345L;
        setUpMockCursorLoader();

        // act
        final CursorLoader loader = unit.getLogMessageByIdCursorLoader(id);

        // assert
        assertThat(loader, is(notNullValue()));
    }

    private void setUpMockCursorLoader() {
        final CursorLoader mockCursorLoader = Mockito.mock(CursorLoader.class);
        doReturn(mockCursorLoader).when(cursorLoaderFactory).getLogItDbLoaderAsCursorLoader(
                any(Context.class),
                any(Uri.class),
                any(String[].class),
                anyString(),
                any(String[].class), anyString());
    }
}
