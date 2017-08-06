package com.acbelter.weatherapp.mvp.view.weather;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acbelter.weatherapp.App;
import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.domain.utils.TemperatureMetric;
import com.acbelter.weatherapp.mvp.presentation.WeatherPresenter;
import com.acbelter.weatherapp.mvp.view.activity.drawer.DrawerLocker;
import com.acbelter.weatherapp.mvp.view.fragment.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

import static com.acbelter.weatherapp.domain.utils.TemperatureMetric.CELSIUS;

public class WeatherFragment extends BaseFragment implements WeatherView {

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.rvWeather)
    RecyclerView recyclerView;

    private WeatherAdapter adapter;

    private Unbinder unbinder;

    @Inject
    WeatherPresenter presenter;

    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        presenter.onAttach(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.unbinder = ButterKnife.bind(this, view);
        setSwipeLayout();
        this.adapter = new WeatherAdapter();
        this.recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        this.recyclerView.addItemDecoration(dividerItemDecoration);
    }


    private void setSwipeLayout() {
        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.updateWeather());
    }


    @Override
    public void onResume() {
        super.onResume();

        presenter.getWeather();
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        App.getInstance().releaseActivityComponent();
        Timber.d("Remove weather component");
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        presenter.onDetach();
    }

    @Override
    protected void setTitle() {
        getActivity().setTitle(R.string.weather);
    }

    @Override
    protected void setDrawableEnabled() {
        ((DrawerLocker) getActivity()).setDrawerEnable(true);
    }

    @Override
    protected void inject() {
        App.getInstance().plusActivityComponent().inject(this);
    }

    @Override
    public void showWeatherLoading() {
        if (swipeRefreshLayout.isRefreshing()) {
            return;
        }
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void showWeather(FullWeatherModel weatherData) {
        adapter.update(weatherData.getForrecast().getWeatherForecast());
        swipeRefreshLayout.setRefreshing(false);
        WeatherRes newWeatherRes = new WeatherRes(weatherData.getCurrentWeatherData());
        setWeatherTextColor(newWeatherRes.getTextColorResId());
        String temperatureStr = getCurrentTemperatureString(weatherData);
//        tvTemperature.setText(temperatureStr);
//        String temperatureMetric = convertMetricToString(weatherData.getCurrentWeatherData().getTemperatureMetric());
//        tvMetric.setText(temperatureMetric);
//        tvCity.setText(weatherData.getCityData().getShortName());
//        contentLayout.setBackgroundColor(
//                ContextCompat.getColor(getContext(), newWeatherRes.getBackgroundColorResId()));
//        weatherImage.setImageResource(newWeatherRes.getWeatherImageResId());
//        weatherView.setWeather(newWeatherRes.getWeatherStatus());
//        weatherView.startAnimation();
        Timber.v("size = " + weatherData.getForrecast().getWeatherForecast().size());
    }

    private String getCurrentTemperatureString(FullWeatherModel fullWeatherModel) {
        int temperature = fullWeatherModel.getCurrentWeatherData().getTemperature();
        return String.valueOf(temperature);
    }

    private String convertMetricToString(TemperatureMetric metric) {
        if (metric == CELSIUS)
            return getString(R.string.celsius);
        else
            return getString(R.string.fahrenheit);
    }

    private void setWeatherTextColor(@ColorRes int colorRes) {
        int color = ContextCompat.getColor(getContext(), colorRes);
//        tvTemperature.setTextColor(color);
//        tvMetric.setTextColor(color);
//        tvCity.setTextColor(color);
    }

    @Override
    public void showError() {
        swipeRefreshLayout.setRefreshing(false);
        Snackbar errorSnackbar =
                Snackbar.make(swipeRefreshLayout, R.string.text_weather_error, Snackbar.LENGTH_LONG);
        errorSnackbar.setAction(R.string.ok, v -> {
        });
        errorSnackbar.show();
    }
}
