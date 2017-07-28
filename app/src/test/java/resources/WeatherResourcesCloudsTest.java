package resources;

import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherType;
import com.acbelter.weatherapp.ui.weather.WeatherRes;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import resources.common.ResourceUtil;
import xyz.matteobattilana.library.Common.Constants;

import static org.junit.Assert.assertEquals;

public class WeatherResourcesCloudsTest {

    private WeatherData weatherData;
    private ResourceUtil resourceUtil;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.weatherData = new WeatherData();
        this.weatherData.setWeatherType(WeatherType.CLOUDS);
        this.resourceUtil = new ResourceUtil(weatherData);
    }

    @Test
    public void testTextColorCloudsDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(R.color.colorTextWeatherDark, weatherRes.getTextColorResId());
    }

    @Test
    public void testTextColorCloudsNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(R.color.colorTextWeatherLight, weatherRes.getTextColorResId());
    }

    @Test
    public void testWeatherImageCloudsDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(R.drawable.img_clouds, weatherRes.getWeatherImageResId());
    }

    @Test
    public void testWeatherImageCloudsNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(R.drawable.img_clouds_night, weatherRes.getWeatherImageResId());
    }

    @Test
    public void testBackgroundColorCloudsDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(R.color.colorBgWeatherClouds, weatherRes.getBackgroundColorResId());
    }

    @Test
    public void testBackgroundColorCloudsNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(R.color.colorBgWeatherNight, weatherRes.getBackgroundColorResId());
    }

    @Test
    public void testStatusCloudsDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(Constants.weatherStatus.SUN, weatherRes.getWeatherStatus());
    }

    @Test
    public void testStatusCloudsNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(Constants.weatherStatus.SUN, weatherRes.getWeatherStatus());
    }
}
