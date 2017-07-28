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

public class WeatherResourcesSunTest {

    private WeatherData weatherData;
    private ResourceUtil resourceUtil;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.weatherData = new WeatherData();
        this.weatherData.setWeatherType(WeatherType.SUN);
        this.resourceUtil = new ResourceUtil(weatherData);
    }

    @Test
    public void testTextColorSunDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(R.color.colorTextWeatherLight, weatherRes.getTextColorResId());
    }

    @Test
    public void testTextColorSunNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(R.color.colorTextWeatherLight, weatherRes.getTextColorResId());
    }

    @Test
    public void testWeatherImageSunDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(R.drawable.img_sun, weatherRes.getWeatherImageResId());
    }

    @Test
    public void testWeatherImageSunNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(R.drawable.img_night, weatherRes.getWeatherImageResId());
    }

    @Test
    public void testBackgroundColorSunDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(R.color.colorBgWeatherSun, weatherRes.getBackgroundColorResId());
    }

    @Test
    public void testBackgroundColorSunNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(R.color.colorBgWeatherNight, weatherRes.getBackgroundColorResId());
    }

    @Test
    public void testStatusSunDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(Constants.weatherStatus.SUN, weatherRes.getWeatherStatus());
    }

    @Test
    public void testStatusSunNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(Constants.weatherStatus.SUN, weatherRes.getWeatherStatus());
    }
}
