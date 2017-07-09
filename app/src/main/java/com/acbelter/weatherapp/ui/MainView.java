package com.acbelter.weatherapp.ui;

import com.acbelter.weatherapp.domain.model.WeatherData;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface MainView extends MvpView {
    void showWeatherLoading();
    void showWeather(List<WeatherData> weatherDataList);
    @StateStrategyType(OneExecutionStateStrategy.class)
    void showError();
}
