package com.acbelter.weatherapp.ui.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.presentation.AboutPresenter;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class AboutFragment extends MvpAppCompatFragment implements AboutView {
    @InjectPresenter
    AboutPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    public static String tag() {
        return AboutFragment.class.getSimpleName();
    }
}
