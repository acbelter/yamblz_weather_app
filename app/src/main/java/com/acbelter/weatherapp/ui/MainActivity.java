package com.acbelter.weatherapp.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.acbelter.weatherapp.App;
import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.WeatherData;
import com.acbelter.weatherapp.presentation.MainPresenter;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MvpAppCompatActivity implements MainView {
    @Inject
    @InjectPresenter
    MainPresenter mMainPresenter;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getComponentManager().addWeatherComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
    }

    @ProvidePresenter
    public MainPresenter provideMainPresenter() {
        return mMainPresenter;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainPresenter.getWeather();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getComponentManager().removeWeatherComponent();
    }

    @Override
    public void showWeatherLoading() {

    }

    @Override
    public void showWeather(List<WeatherData> weatherDataList) {

    }

    @Override
    public void showError() {

    }
}
