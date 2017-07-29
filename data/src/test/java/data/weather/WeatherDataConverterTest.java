package data.weather;

import com.acbelter.weatherapp.data.repository.weather.WeatherDataConverter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class WeatherDataConverterTest {

    @Test
    public void testWeatherConverter() {

    }

    @Test(expected = IllegalArgumentException.class)
    public void testWeatherConverterToNull() {
        assertNull(WeatherDataConverter.fromDatabaseData(null));
    }
}
