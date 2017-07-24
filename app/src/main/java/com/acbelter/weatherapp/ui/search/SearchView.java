package com.acbelter.weatherapp.ui.search;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface SearchView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void close();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void updateCityList(List<CityData> locations);
}
