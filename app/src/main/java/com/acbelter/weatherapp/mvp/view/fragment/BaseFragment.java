package com.acbelter.weatherapp.mvp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.inputmethod.InputMethodManager;

import com.acbelter.weatherapp.App;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        inject();
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);

        setDrawableEnabled();
        setTitle();
//        hideKeyboard();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        App.getInstance().releaseActivityComponent();
    }

    protected abstract void setTitle();

    protected abstract void setDrawableEnabled();

    protected abstract void inject();

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
    }

}
