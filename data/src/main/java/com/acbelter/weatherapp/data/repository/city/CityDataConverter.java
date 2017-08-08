package com.acbelter.weatherapp.data.repository.city;

import com.acbelter.weatherapp.data.locationmodel.Location;
import com.acbelter.weatherapp.data.placesmodel.Places;
import com.acbelter.weatherapp.data.placesmodel.Prediction;
import com.acbelter.weatherapp.domain.model.city.AutocompleteData;
import com.acbelter.weatherapp.domain.model.city.CityData;

import java.util.ArrayList;
import java.util.List;

import static com.acbelter.weatherapp.data.network.ApiErrors.PlacesApiErrors.OK;

public class CityDataConverter {

    public static List<AutocompleteData> fromPlacesToDataList(Places places) {

        if (places == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }

        if (!places.getStatus().equals(OK.getError())) {
            return null;
        }

        List<AutocompleteData> cityList = new ArrayList<>();
        List<Prediction> predictions = places.getPredictions();
        for (Prediction prediction : predictions) {
            AutocompleteData autocompleteData = convert(prediction);
            cityList.add(autocompleteData);
        }
        return cityList;
    }

    public static CityData fromLocationToCityData(Location location) {

        if (location == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }

        if (!location.getStatus().equals(OK.getError())) {
            return null;
        }
        CityData cityData = new CityData();
        cityData.setShortName(location.getResult().getName());
        cityData.setLatitude(location.getResult().getGeometry().getLocation().getLat());
        cityData.setLongitude(location.getResult().getGeometry().getLocation().getLng());
        return cityData;
    }

    public static AutocompleteData convert(Prediction prediction) {
        return new AutocompleteData(prediction.getDescription(), prediction.getPlaceId());
    }
}
