package com.acbelter.weatherapp.mvp.view.activity;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.mvp.view.common.BaseView;

import java.util.List;

public interface MainActivityView extends BaseView {

    void showCityList(List<CityData> cities);

    void showWeather();
}
