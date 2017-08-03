package com.acbelter.weatherapp.mvp.view.weather;

import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface WeatherView extends MvpView {
    void showWeatherLoading();

    void showWeather(FullWeatherModel weatherData);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showError();
}
