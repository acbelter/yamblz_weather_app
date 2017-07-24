package com.acbelter.weatherapp.ui.weather;

import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface WeatherView extends MvpView {
    void showWeatherLoading();
    void showWeather(WeatherData weatherData, long updateTimestamp);
    @StateStrategyType(OneExecutionStateStrategy.class)
    void showError();
}
