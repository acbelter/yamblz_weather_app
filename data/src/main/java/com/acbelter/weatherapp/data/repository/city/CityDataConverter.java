package com.acbelter.weatherapp.data.repository.city;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.acbelter.weatherapp.data.locationmodel.Location;
import com.acbelter.weatherapp.data.placesmodel.Places;
import com.acbelter.weatherapp.data.placesmodel.Prediction;
import com.acbelter.weatherapp.domain.model.city.AutocompleteData;
import com.acbelter.weatherapp.domain.model.city.CityData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.acbelter.weatherapp.data.network.ApiErrors.PlacesApiErrors.OK;

class CityDataConverter {

    private CityDataConverter() {
    }

    static
    @Nullable
    List<AutocompleteData> fromPlacesToDataList(@Nullable Places places) {

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

    static
    @Nullable
    CityData fromLocationToCityData(@Nullable Location location) {

        if (location == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }

        if (!location.getStatus().equals(OK.getError())) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        long timestamp = calendar.getTimeInMillis();
        return new CityData.Builder(location.getResult().getGeometry().getLocation().getLat()
                , location.getResult().getGeometry().getLocation().getLng(), timestamp)
                .shortName(location.getResult().getName()).build();
    }

    static
    @NonNull
    AutocompleteData convert(@NonNull Prediction prediction) {
        return new AutocompleteData(prediction.getDescription(), prediction.getPlaceId());
    }
}
