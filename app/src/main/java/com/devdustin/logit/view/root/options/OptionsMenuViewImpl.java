package com.devdustin.logit.view.root.options;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.devdustin.logit.R;

public class OptionsMenuViewImpl implements OptionsMenuView {

    private final Menu menu;

    public OptionsMenuViewImpl(MenuInflater inflater, Menu menu) {
        inflater.inflate(R.menu.main, menu);
        this.menu = menu;
    }

    @Override
    public View getRootView() {
        return null;
    }

    @Override
    public Bundle getState() {
        final Bundle state = new Bundle();
        state.putBoolean(MENU_HAS_VISIBLE_ITEMS, menu.hasVisibleItems());
        return state;
    }
}
