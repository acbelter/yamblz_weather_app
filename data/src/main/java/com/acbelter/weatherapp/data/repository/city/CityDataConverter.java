package com.acbelter.weatherapp.data.repository.city;

import com.acbelter.weatherapp.data.locationmodel.Location;
import com.acbelter.weatherapp.domain.model.city.CityData;

import static com.acbelter.weatherapp.data.network.ApiErrors.PlacesApiErrors.OK;

public class CityDataConverter {

    public static CityData fromNetworkData(Location location) {

        if (location == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }

        if (!location.getStatus().equals(OK.getError())) {
            return null;
        }

        CityData cityData = new CityData();
        cityData.setFormattedAddress(location.getResult().getFormattedAddress());
        cityData.setLatitude(location.getResult().getGeometry().getLocation().getLat());
        cityData.setLongitude(location.getResult().getGeometry().getLocation().getLng());
        return cityData;
    }
}
