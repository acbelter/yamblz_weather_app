package com.acbelter.weatherapp.ui.weather;

import com.acbelter.weatherapp.domain.model.WeatherData;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface WeatherView extends MvpView {
    void showWeatherLoading();
    void showWeather(List<WeatherData> weatherDataList);
    @StateStrategyType(OneExecutionStateStrategy.class)
    void showError();
}
