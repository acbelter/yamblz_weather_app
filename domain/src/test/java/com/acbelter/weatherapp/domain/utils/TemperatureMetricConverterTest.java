package com.acbelter.weatherapp.domain.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.acbelter.weatherapp.domain.utils.TemperatureMetric.CELSIUS;
import static com.acbelter.weatherapp.domain.utils.TemperatureMetric.FAHRENHEIT;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TemperatureMetricConverterTest {

    @Test
    public void testGetSupportedTemperature() {
        double temperatureK = 293.15;
        int temperatureC = 20;
        int temperatureF = 68;

        assertEquals(temperatureC, TemperatureMetricConverter.getSupportedTemperature(temperatureK, CELSIUS));
        assertEquals(temperatureF, TemperatureMetricConverter.getSupportedTemperature(temperatureK, FAHRENHEIT));

    }
}
