package com.devdustin.logit.controller.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.devdustin.logit.R;
import com.devdustin.logit.controller.fragment.AbstractFragment;
import com.devdustin.logit.controller.fragment.LogMessagesFragment;
import com.devdustin.logit.database.repository.LogItDbRepository;
import com.devdustin.logit.database.repository.LogItDbRepositoryImpl;
import com.devdustin.logit.event.LogMessageDeletedEvent;
import com.devdustin.logit.event.LogMessageSubmittedEvent;
import com.devdustin.logit.view.root.RootView;
import com.devdustin.logit.view.root.RootViewImpl;
import com.devdustin.logit.view.root.options.OptionsMenuView;
import com.devdustin.logit.view.root.options.OptionsMenuViewImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements AbstractFragment.AbstractFragmentCallback, RootView.RootViewListener {

    private RootView rootView;
    private OptionsMenuView optionsMenuView;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = new RootViewImpl(this, null);
        setContentView(rootView.getRootView());
        rootView.setListener(this);

        if (savedInstanceState == null) {
            // show default fragment
            final boolean refresh = false;
            loadDefaultFragment(refresh);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        optionsMenuView = new OptionsMenuViewImpl(getMenuInflater(), menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            final Bundle state = optionsMenuView.getState();
            final boolean hasVisibleItems = state.getBoolean(OptionsMenuView.MENU_HAS_VISIBLE_ITEMS);
            Log.i("visibile menus", String.valueOf(hasVisibleItems));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void replaceFragment(Class<? extends Fragment> theClass, boolean addToBackStack, Bundle args, boolean refresh) {
        if (!refresh && isFragmentShown(theClass)) {
            return;
        }

        final FragmentTransaction transaction = getFragmentManager().beginTransaction();
        final Fragment newFragment;

        try {
            newFragment = theClass.newInstance();
            if (args != null) {
                newFragment.setArguments(args);
            }
        } catch (InstantiationException e) {
            Log.e(MainActivity.class.getSimpleName(), e.getMessage());
            return;
        } catch (IllegalAccessException e) {
            Log.e(MainActivity.class.getSimpleName(), e.getMessage());
            return;
        }

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }

        transaction.replace(R.id.frame_contents, newFragment, theClass.getSimpleName());
        transaction.commit();
    }

    @Override
    public void onSubmitClick() {
        LogItDbRepository repo = new LogItDbRepositoryImpl(this, null, null);
        final Bundle state = rootView.getState();
        final String message = state.getString(RootView.NEW_LOG_KEY);
        repo.submitLogMessage(message);
    }

    private boolean isFragmentShown(Class<? extends Fragment> theClass) {
        final Fragment currentFragment = getFragmentManager().findFragmentById(R.id.frame_contents);

        return (currentFragment == null && theClass == null)
                || currentFragment != null && theClass.isInstance(currentFragment);
    }

    private void loadDefaultFragment(boolean refresh) {
        replaceFragment(LogMessagesFragment.class, false, null, refresh);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogMessageSubmittedEvent(LogMessageSubmittedEvent event) {
        final boolean refresh = true;
        loadDefaultFragment(refresh);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogMessageDeletedEvent(LogMessageDeletedEvent event) {
        final boolean refresh = true;
        loadDefaultFragment(refresh);
    }
}
