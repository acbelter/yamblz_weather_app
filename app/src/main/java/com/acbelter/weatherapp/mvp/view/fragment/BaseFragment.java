package com.acbelter.weatherapp.mvp.view.fragment;

import android.os.Bundle;

import com.acbelter.weatherapp.App;
import com.arellomobile.mvp.MvpAppCompatFragment;

public abstract class BaseFragment extends MvpAppCompatFragment {

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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        App.getInstance().releaseActivityComponent();
    }

    protected abstract void setTitle();

    protected abstract void setDrawableEnabled();

    protected abstract void inject();

}
