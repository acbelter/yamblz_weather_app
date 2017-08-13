package com.acbelter.weatherapp.data.repository.city;

import com.acbelter.weatherapp.data.locationmodel.Geometry;
import com.acbelter.weatherapp.data.locationmodel.Location;
import com.acbelter.weatherapp.data.locationmodel.Location_;
import com.acbelter.weatherapp.data.locationmodel.Result;
import com.acbelter.weatherapp.data.placesmodel.Places;
import com.acbelter.weatherapp.data.placesmodel.Prediction;
import com.acbelter.weatherapp.domain.model.city.AutocompleteData;
import com.acbelter.weatherapp.domain.model.city.CityData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.acbelter.weatherapp.data.network.ApiErrors.PlacesApiErrors.OK;
import static com.acbelter.weatherapp.data.network.ApiErrors.PlacesApiErrors.ZERO_RESULTS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class CityDataConverterTest {

    private Location location;
    private Places places;

    @Before
    public void setUp() {
        initLocation();
        initPlaces();
    }

    @Test
    public void testPlacesCodeError() {
        places.setStatus(ZERO_RESULTS);
        assertNull(CityDataConverter.fromPlacesToDataList(places));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlacesConverterToNull() {
        assertNull(CityDataConverter.fromPlacesToDataList(null));
    }

    @Test
    public void testConvertFromPlacesToDataList() {
        List<AutocompleteData> dataList = new ArrayList<>();
        AutocompleteData autocompleteData = new AutocompleteData("description", "id");
        dataList.add(autocompleteData);
        assertEquals(dataList, CityDataConverter.fromPlacesToDataList(places));
    }

    @Test
    public void testLocationCodeError() {
        location.setStatus(ZERO_RESULTS);
        assertNull(CityDataConverter.fromLocationToCityData(location));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLocationConverterToNull() {
        assertNull(CityDataConverter.fromLocationToCityData(null));
    }

    @Test
    public void testConvertFromLocationToCityData() {
        CityData cityData = new CityData.Builder(55.31, 54.01, 0L).build();
        assertEquals(cityData, CityDataConverter.fromLocationToCityData(location));
    }

    private void initLocation() {
        location = new Location();
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

    private void initPlaces() {
        places = new Places();
        List<Prediction> predictions = new ArrayList<>();
        Prediction prediction = new Prediction();
        prediction.setDescription("description");
        prediction.setPlaceId("id");
        predictions.add(prediction);
        places.setPredictions(predictions);
        places.setStatus(OK);
    }
}
