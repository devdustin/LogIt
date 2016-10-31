package com.devdustin.logit.controller.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import com.devdustin.logit.database.repository.LogItDbRepository;
import com.devdustin.logit.database.repository.LogItDbRepositoryImpl;

public class AbstractFragment extends Fragment {

    private AbstractFragmentCallback callback;
    private LogItDbRepository repository;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callback = (AbstractFragmentCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(String.format("%s must implement %s", context.toString(), AbstractFragmentCallback.class.getCanonicalName()));
        }
    }

    public void replaceFragment(Class<? extends Fragment> theClass, boolean addToBackStack, Bundle args, boolean refresh) {
        callback.replaceFragment(theClass, addToBackStack, args, refresh);
    }

    public interface AbstractFragmentCallback {
        void replaceFragment(Class<? extends Fragment> theClass, boolean addToBackStack, Bundle args, boolean refresh);
    }

    protected LogItDbRepository getRepository() {
        if (repository == null) {
            // todo: inject this if possible
            repository = new LogItDbRepositoryImpl(getActivity());
        }

        return repository;
    }
}
