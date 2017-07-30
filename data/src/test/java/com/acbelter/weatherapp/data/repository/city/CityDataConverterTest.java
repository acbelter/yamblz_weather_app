package com.acbelter.weatherapp.data.repository.city;

import com.acbelter.weatherapp.data.locationmodel.Geometry;
import com.acbelter.weatherapp.data.locationmodel.Location;
import com.acbelter.weatherapp.data.locationmodel.Location_;
import com.acbelter.weatherapp.data.locationmodel.Result;
import com.acbelter.weatherapp.domain.model.city.CityData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.acbelter.weatherapp.data.network.ApiErrors.PlacesApiErrors.OK;
import static com.acbelter.weatherapp.data.network.ApiErrors.PlacesApiErrors.ZERO_RESULTS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class CityDataConverterTest {

    private Location location;
    private CityData cityData;

    @Before
    public void setUp() {
        location = new Location();
        cityData = new CityData();
    }

    @Test
    public void testCodeError() {
        location.setStatus(ZERO_RESULTS);
        assertNull(CityDataConverter.fromNetworkData(location));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCityConverterToNull() {
        assertNull(CityDataConverter.fromNetworkData(null));
    }

    @Test
    public void testConverting() {
        initLocation();
        initCityData();

        CityData testCityData = CityDataConverter.fromNetworkData(location);
        assertEquals(testCityData.getFormattedAddress(), cityData.getFormattedAddress());
    }

    private void initLocation() {
        Result result = new Result();
        result.setFormattedAddress("Moscow, Yandex");
        Geometry geometry = new Geometry();
        Location_ location_ = new Location_();
        location_.setLat(55.31);
        location_.setLng(54.01);
        geometry.setLocation(location_);
        result.setGeometry(geometry);
        location.setResult(result);
        location.setStatus(OK);
    }

    private void initCityData() {
        cityData.setFormattedAddress("Moscow, Yandex");
        cityData.setLatitude(55.31);
        cityData.setLongitude(54.01);
    }
}
