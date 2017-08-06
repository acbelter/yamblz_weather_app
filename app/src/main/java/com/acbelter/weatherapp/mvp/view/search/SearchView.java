package com.acbelter.weatherapp.mvp.view.search;

import com.acbelter.weatherapp.domain.model.city.AutocompleteData;
import com.acbelter.weatherapp.mvp.view.common.BaseView;

import java.util.List;

public interface SearchView extends BaseView {

    void updateCityList(List<AutocompleteData> locations);

    void showError();

    void close();
}
