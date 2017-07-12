package com.acbelter.weatherapp.ui.weather;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acbelter.weatherapp.App;
import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.WeatherData;
import com.acbelter.weatherapp.presentation.WeatherPresenter;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

public class WeatherFragment extends MvpAppCompatFragment implements WeatherView {
    @Inject
    @InjectPresenter
    WeatherPresenter mPresenter;
    @BindView(R.id.root_layout)
    ViewGroup mRootLayout;
    @BindView(R.id.date_text)
    TextView mDateText;
    @BindView(R.id.temperature_text)
    TextView mTemperatureText;
    @BindView(R.id.units_text)
    TextView mUnitsText;
    @BindView(R.id.location_text)
    TextView mLocationText;
    @BindView(R.id.weather_image)
    ImageView mWeatherImage;
    private Unbinder mUnbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getComponentManager().addWeatherComponent().inject(this);
        super.onCreate(savedInstanceState);
        Timber.d("Add weather component");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        App.getComponentManager().removeWeatherComponent();
        Timber.d("Remove weather component");
        mUnbinder.unbind();
    }

    @ProvidePresenter
    public WeatherPresenter provideWeatherPresenter() {
        return mPresenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        initViews(inflater.getContext());

        return view;
    }

    private void initViews(Context context) {
        mRootLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        mDateText.setText("12 July 2017");
        mTemperatureText.setText("21");
        mUnitsText.setText("ºC");
        mLocationText.setText("Moscow region, Dolgoprudny");
        mWeatherImage.setImageResource(R.drawable.img_sun);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getWeather();
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

    public static String tag() {
        return WeatherFragment.class.getSimpleName();
    }
}
