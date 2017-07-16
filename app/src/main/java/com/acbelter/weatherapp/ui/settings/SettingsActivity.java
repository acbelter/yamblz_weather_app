package com.acbelter.weatherapp.ui.settings;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.acbelter.weatherapp.App;
import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.presentation.SettingsPresenter;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import javax.inject.Inject;

import timber.log.Timber;

public class SettingsActivity extends MvpAppCompatActivity implements
        SettingsView, SettingsFragment.SettingsListener {
    @Inject
    @InjectPresenter
    SettingsPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getComponentManager().getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, new SettingsFragment(), SettingsFragment.tag());
            ft.commit();
        }
    }

    @ProvidePresenter
    public SettingsPresenter provideSettingsPresenter() {
        return mPresenter;
    }

    @Override
    public void onUpdateIntervalChanged(int newUpdateInterval) {
        Timber.d("Weather update interval is changed: " + newUpdateInterval);
        mPresenter.restartWeatherUpdating(this, newUpdateInterval);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                close();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    public void onBackPressed() {
        close();
    }

    @Override
    public void close() {
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
