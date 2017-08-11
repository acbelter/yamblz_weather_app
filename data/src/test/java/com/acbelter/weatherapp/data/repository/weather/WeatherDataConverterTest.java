package com.acbelter.weatherapp.data.repository.weather;

import com.acbelter.weatherapp.data.weathermodel.currentweather.CurrentWeather;
import com.acbelter.weatherapp.data.weathermodel.forecast.ForecastElement;
import com.acbelter.weatherapp.data.weathermodel.forecast.ForecastWeather;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.ForecastWeatherElement;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;
import com.acbelter.weatherapp.domain.model.weather.WeatherType;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.acbelter.weatherapp.domain.model.weather.WeatherType.CLOUDS;
import static com.acbelter.weatherapp.domain.model.weather.WeatherType.RAIN;
import static com.acbelter.weatherapp.domain.model.weather.WeatherType.SNOW;
import static com.acbelter.weatherapp.domain.model.weather.WeatherType.STORM;
import static com.acbelter.weatherapp.domain.model.weather.WeatherType.SUN;
import static com.acbelter.weatherapp.domain.utils.TemperatureMetric.CELSIUS;
import static com.acbelter.weatherapp.domain.utils.TemperatureMetric.FAHRENHEIT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class WeatherDataConverterTest {

    private CurrentWeatherFavorites currentWeatherFavorites;
    private ForecastWeatherElement forecastWeatherElement;

    @Before
    public void setUp() {
        initCurrentWeatherFavorites();
        initForecastWeather();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateCurrentWeatherMetricToNull() {
        assertNull(WeatherDataConverter.updateCurrentWeatherMetric(null, CELSIUS));
    }

    @Test(expected = NullPointerException.class)
    public void testCurrentWeatherMetricWithNullMetricToNull() {
        assertNull(WeatherDataConverter.updateCurrentWeatherMetric(currentWeatherFavorites, null));
    }

    @Test
    public void testUpdateCurrentWeatherMetric() {
        currentWeatherFavorites.setTemperatureMetric(FAHRENHEIT);
        assertEquals(currentWeatherFavorites, WeatherDataConverter.updateCurrentWeatherMetric(currentWeatherFavorites, CELSIUS));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateForecastWeatherMetricToNull() {
        assertNull(WeatherDataConverter.updateForecastWeatherMetric(null, CELSIUS));
    }

    @Test(expected = NullPointerException.class)
    public void testForecastWeatherMetricWithNullMetricToNull() {
        assertNull(WeatherDataConverter.updateForecastWeatherMetric(forecastWeatherElement, null));
    }

    @Test
    public void testUpdateForecastWeatherMetric() {
        currentWeatherFavorites.setTemperatureMetric(FAHRENHEIT);
        assertEquals(forecastWeatherElement, WeatherDataConverter.updateForecastWeatherMetric(forecastWeatherElement, FAHRENHEIT));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromNWWeatherDataToCurrentToNull() {
        CityData cityData = new CityData.Builder(0, 0, 0L).build();
        WeatherParams weatherParams = new WeatherParams(cityData, CELSIUS);
        assertNull(WeatherDataConverter.fromNWWeatherDataToCurrentWeatherData(null, weatherParams));
    }

    @Test
    public void testFromNWWithWrongCode() {
        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setCode(201);
        CityData cityData = new CityData.Builder(0, 0, 0L).build();
        WeatherParams weatherParams = new WeatherParams(cityData, CELSIUS);
        assertNull(WeatherDataConverter.fromNWWeatherDataToCurrentWeatherData(currentWeather, weatherParams));
    }

    @Test
    public void testFromNWWeatherDataToCurrentWeatherData() {
        CityData cityData = new CityData.Builder(0, 0, 0L).build();
        WeatherParams weatherParams = new WeatherParams(cityData, CELSIUS);
        CurrentWeather currentWeather = new Gson().fromJson("{\"coord\":{\"lon\":41.44,\"lat\":52.72},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"02d\"}],\"base\":\"stations\",\"main\":{\"temp\":301.124,\"pressure\":1012.19,\"humidity\":40,\"temp_min\":301.124,\"temp_max\":301.124,\"sea_level\":1030.76,\"grnd_level\":1012.19},\"wind\":{\"speed\":3.65,\"deg\":264.501},\"clouds\":{\"all\":8},\"dt\":1502457955,\"sys\":{\"message\":0.0025,\"country\":\"RU\",\"sunrise\":1502416221,\"sunset\":1502470031},\"id\":484646,\"name\":\"Tambov\",\"cod\":200}", CurrentWeather.class);
        CurrentWeatherFavorites currentWeatherFavorites = WeatherDataConverter.fromNWWeatherDataToCurrentWeatherData(currentWeather, weatherParams);
        assertEquals(currentWeatherFavorites.getTemperature(), currentWeather.getMain().getTemp(), 0.0);
        assertEquals(currentWeatherFavorites.getCityData(), weatherParams.getCityData());
        assertEquals(currentWeatherFavorites.getTemperatureMetric(), weatherParams.getMetric());
        assertEquals(currentWeatherFavorites.getWeatherType(), WeatherDataConverter.extractWeatherType(currentWeather.getWeather()));
        assertEquals(currentWeatherFavorites.getTimestamp(), currentWeather.getDt() * 1000L);
        assertEquals(currentWeatherFavorites.getSunriseTimestamp(), currentWeather.getSys().getSunrise() * 1000L);
        assertEquals(currentWeatherFavorites.getSunsetTimestamp(), currentWeather.getSys().getSunset() * 1000L);
        assertEquals(currentWeatherFavorites.getPressure(), currentWeather.getMain().getPressure(), 0.0);
        assertEquals(currentWeatherFavorites.getHumidity(), currentWeather.getMain().getHumidity());
        assertEquals(currentWeatherFavorites.getDescription(), currentWeather.getWeather().get(0).getDescription());
        assertEquals(currentWeatherFavorites.getWindSpeed(), currentWeather.getWind().getSpeed(), 0.0);
        assertEquals(currentWeatherFavorites.getMinTemp(), currentWeather.getMain().getTempMin(), 0.0);
        assertEquals(currentWeatherFavorites.getMaxTemp(), currentWeather.getMain().getTempMax(), 0.0);
    }

    @Test
    public void testFromForecastElementToWeatherForecast() {
        CityData cityData = new CityData.Builder(0, 0, 0L).build();
        WeatherParams weatherParams = new WeatherParams(cityData, CELSIUS);
        ForecastWeather forecastWeather = new Gson().fromJson("{\"city\":{\"id\":484646,\"name\":\"Tambov\",\"coord\":{\"lon\":41.4339,\"lat\":52.7317},\"country\":\"RU\",\"population\":290933},\"cod\":\"200\",\"message\":3.0984934,\"cnt\":16,\"list\":[{\"dt\":1502442000,\"temp\":{\"day\":300.84,\"min\":292.28,\"max\":300.84,\"night\":292.28,\"eve\":300.61,\"morn\":300.84},\"pressure\":1012.19,\"humidity\":40,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"02d\"}],\"speed\":3.65,\"deg\":265,\"clouds\":8},{\"dt\":1502528400,\"temp\":{\"day\":299.36,\"min\":290.89,\"max\":301.23,\"night\":291.24,\"eve\":300.22,\"morn\":290.89},\"pressure\":1008.59,\"humidity\":64,\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02d\"}],\"speed\":4.37,\"deg\":356,\"clouds\":20},{\"dt\":1502614800,\"temp\":{\"day\":299.35,\"min\":288.34,\"max\":301.37,\"night\":292.17,\"eve\":301.05,\"morn\":288.34},\"pressure\":1010.08,\"humidity\":52,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":3.48,\"deg\":88,\"clouds\":0},{\"dt\":1502701200,\"temp\":{\"day\":303.34,\"min\":290.65,\"max\":305.33,\"night\":292.84,\"eve\":304.8,\"morn\":290.65},\"pressure\":1009.19,\"humidity\":42,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":3.91,\"deg\":169,\"clouds\":20,\"rain\":0.55},{\"dt\":1502787600,\"temp\":{\"day\":302.52,\"min\":293.29,\"max\":302.52,\"night\":293.29,\"eve\":297.39,\"morn\":298.94},\"pressure\":1016.09,\"humidity\":0,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":2.21,\"deg\":78,\"clouds\":24},{\"dt\":1502874000,\"temp\":{\"day\":298.45,\"min\":288.77,\"max\":298.45,\"night\":288.77,\"eve\":293.35,\"morn\":295.73},\"pressure\":1019.72,\"humidity\":0,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":5.31,\"deg\":43,\"clouds\":4},{\"dt\":1502960400,\"temp\":{\"day\":300.81,\"min\":288.38,\"max\":300.81,\"night\":288.38,\"eve\":294.63,\"morn\":295.67},\"pressure\":1017.92,\"humidity\":0,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":1.14,\"deg\":84,\"clouds\":33},{\"dt\":1503046800,\"temp\":{\"day\":301.75,\"min\":288.24,\"max\":301.75,\"night\":288.24,\"eve\":294.8,\"morn\":298.77},\"pressure\":1008.49,\"humidity\":0,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":6.15,\"deg\":263,\"clouds\":34,\"rain\":0.68},{\"dt\":1503133200,\"temp\":{\"day\":296.72,\"min\":286.05,\"max\":296.72,\"night\":286.05,\"eve\":291.21,\"morn\":293.97},\"pressure\":1004.9,\"humidity\":0,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":4.37,\"deg\":331,\"clouds\":0},{\"dt\":1503219600,\"temp\":{\"day\":295.05,\"min\":287.56,\"max\":295.05,\"night\":287.56,\"eve\":290.39,\"morn\":292.15},\"pressure\":1003.7,\"humidity\":0,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":4.95,\"deg\":359,\"clouds\":3},{\"dt\":1503306000,\"temp\":{\"day\":294.35,\"min\":282.83,\"max\":294.35,\"night\":282.83,\"eve\":288.91,\"morn\":290.04},\"pressure\":1008.93,\"humidity\":0,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":6.21,\"deg\":31,\"clouds\":0},{\"dt\":1503392400,\"temp\":{\"day\":294.16,\"min\":283.36,\"max\":294.16,\"night\":283.36,\"eve\":289.5,\"morn\":289.67},\"pressure\":1006.03,\"humidity\":0,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":6.49,\"deg\":338,\"clouds\":5},{\"dt\":1503478800,\"temp\":{\"day\":291.98,\"min\":279.77,\"max\":291.98,\"night\":279.77,\"eve\":285.68,\"morn\":287.32},\"pressure\":1003.99,\"humidity\":0,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":4.15,\"deg\":27,\"clouds\":1},{\"dt\":1503565200,\"temp\":{\"day\":294.04,\"min\":280.52,\"max\":294.04,\"night\":280.52,\"eve\":285.99,\"morn\":290.3},\"pressure\":1007.56,\"humidity\":0,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":2.07,\"deg\":29,\"clouds\":0},{\"dt\":1503651600,\"temp\":{\"day\":297.35,\"min\":282.82,\"max\":297.35,\"night\":282.82,\"eve\":287.95,\"morn\":292.09},\"pressure\":1011.17,\"humidity\":0,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":2.44,\"deg\":351,\"clouds\":0},{\"dt\":1503738000,\"temp\":{\"day\":282.82,\"min\":282.82,\"max\":282.82,\"night\":282.82,\"eve\":282.82,\"morn\":282.82},\"pressure\":1013.14,\"humidity\":0,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01ddd\"}],\"speed\":1.23,\"deg\":12,\"clouds\":0}]}", ForecastWeather.class);
        ForecastElement forecastElement = forecastWeather.getForecastElement().get(0);
        ForecastWeatherElement forecastWeatherElement = WeatherDataConverter.fromForecastElementToWeatherForecast(forecastElement, weatherParams);

        DateFormat df = new SimpleDateFormat("dd MMMM", Locale.getDefault());
        Date date = new Date();
        long curTime = forecastElement.getDt() * 1000L;
        date.setTime(curTime);
        String dateStr = df.format(date);
        assertEquals(forecastWeatherElement.getDate(), dateStr);
        assertEquals(forecastWeatherElement.getMaxTemp(), forecastElement.getTemp().getMax(), 0.0);
        assertEquals(forecastWeatherElement.getMinTemp(), forecastElement.getTemp().getMin(), 0.0);
        assertEquals(forecastWeatherElement.getTemperatureMetric(), weatherParams.getMetric());
        assertEquals(forecastWeatherElement.getWeatherType(), WeatherDataConverter.extractWeatherType(forecastElement.getWeather()));
        assertEquals(forecastWeatherElement.getPressure(), forecastElement.getPressure(), 0.0);
        assertEquals(forecastWeatherElement.getHumidity(), forecastElement.getHumidity());
        assertEquals(forecastWeatherElement.getDescription(), forecastElement.getWeather().get(0).getDescription());
        assertEquals(forecastWeatherElement.getWindSpeed(), forecastElement.getSpeed(), 0.0);
    }

    @Test
    public void testExtractWeatherThunderstormType() {
        CurrentWeather currentWeather = new Gson().fromJson("{\"coord\":{\"lon\":-0.13,\"lat\":51.51},\"weather\":[{\"id\":803,\"main\":\"Thunderstorm\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"base\":\"stations\",\"main\":{\"temp\":293.45,\"pressure\":1018,\"humidity\":49,\"temp_min\":293.15,\"temp_max\":294.15},\"visibility\":10000,\"wind\":{\"speed\":5.7,\"deg\":260},\"clouds\":{\"all\":76},\"dt\":1502459400,\"sys\":{\"type\":1,\"id\":5091,\"message\":0.0043,\"country\":\"GB\",\"sunrise\":1502426446,\"sunset\":1502479760},\"id\":2643743,\"name\":\"London\",\"cod\":200}", CurrentWeather.class);
        WeatherType weatherType = WeatherDataConverter.extractWeatherType(currentWeather.getWeather());
        assertEquals(weatherType, STORM);
    }

    @Test
    public void testExtractWeatherRainType() {
        CurrentWeather currentWeather = new Gson().fromJson("{\"coord\":{\"lon\":-0.13,\"lat\":51.51},\"weather\":[{\"id\":803,\"main\":\"Rain\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"base\":\"stations\",\"main\":{\"temp\":293.45,\"pressure\":1018,\"humidity\":49,\"temp_min\":293.15,\"temp_max\":294.15},\"visibility\":10000,\"wind\":{\"speed\":5.7,\"deg\":260},\"clouds\":{\"all\":76},\"dt\":1502459400,\"sys\":{\"type\":1,\"id\":5091,\"message\":0.0043,\"country\":\"GB\",\"sunrise\":1502426446,\"sunset\":1502479760},\"id\":2643743,\"name\":\"London\",\"cod\":200}", CurrentWeather.class);
        WeatherType weatherType = WeatherDataConverter.extractWeatherType(currentWeather.getWeather());
        assertEquals(weatherType, RAIN);
    }

    @Test
    public void testExtractWeatherDrizzleType() {
        CurrentWeather currentWeather = new Gson().fromJson("{\"coord\":{\"lon\":-0.13,\"lat\":51.51},\"weather\":[{\"id\":803,\"main\":\"Drizzle\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"base\":\"stations\",\"main\":{\"temp\":293.45,\"pressure\":1018,\"humidity\":49,\"temp_min\":293.15,\"temp_max\":294.15},\"visibility\":10000,\"wind\":{\"speed\":5.7,\"deg\":260},\"clouds\":{\"all\":76},\"dt\":1502459400,\"sys\":{\"type\":1,\"id\":5091,\"message\":0.0043,\"country\":\"GB\",\"sunrise\":1502426446,\"sunset\":1502479760},\"id\":2643743,\"name\":\"London\",\"cod\":200}", CurrentWeather.class);
        WeatherType weatherType = WeatherDataConverter.extractWeatherType(currentWeather.getWeather());
        assertEquals(weatherType, RAIN);
    }

    @Test
    public void testExtractWeatherSnowType() {
        CurrentWeather currentWeather = new Gson().fromJson("{\"coord\":{\"lon\":-0.13,\"lat\":51.51},\"weather\":[{\"id\":803,\"main\":\"Snow\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"base\":\"stations\",\"main\":{\"temp\":293.45,\"pressure\":1018,\"humidity\":49,\"temp_min\":293.15,\"temp_max\":294.15},\"visibility\":10000,\"wind\":{\"speed\":5.7,\"deg\":260},\"clouds\":{\"all\":76},\"dt\":1502459400,\"sys\":{\"type\":1,\"id\":5091,\"message\":0.0043,\"country\":\"GB\",\"sunrise\":1502426446,\"sunset\":1502479760},\"id\":2643743,\"name\":\"London\",\"cod\":200}", CurrentWeather.class);
        WeatherType weatherType = WeatherDataConverter.extractWeatherType(currentWeather.getWeather());
        assertEquals(weatherType, SNOW);
    }

    @Test
    public void testExtractWeatherCloudsType() {
        CurrentWeather currentWeather = new Gson().fromJson("{\"coord\":{\"lon\":-0.13,\"lat\":51.51},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"base\":\"stations\",\"main\":{\"temp\":293.45,\"pressure\":1018,\"humidity\":49,\"temp_min\":293.15,\"temp_max\":294.15},\"visibility\":10000,\"wind\":{\"speed\":5.7,\"deg\":260},\"clouds\":{\"all\":76},\"dt\":1502459400,\"sys\":{\"type\":1,\"id\":5091,\"message\":0.0043,\"country\":\"GB\",\"sunrise\":1502426446,\"sunset\":1502479760},\"id\":2643743,\"name\":\"London\",\"cod\":200}", CurrentWeather.class);
        WeatherType weatherType = WeatherDataConverter.extractWeatherType(currentWeather.getWeather());
        assertEquals(weatherType, CLOUDS);
    }

    @Test
    public void testExtractWeatherSunType() {
        CurrentWeather currentWeather = new Gson().fromJson("{\"coord\":{\"lon\":41.44,\"lat\":52.72},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"02d\"}],\"base\":\"stations\",\"main\":{\"temp\":301.124,\"pressure\":1012.19,\"humidity\":40,\"temp_min\":301.124,\"temp_max\":301.124,\"sea_level\":1030.76,\"grnd_level\":1012.19},\"wind\":{\"speed\":3.65,\"deg\":264.501},\"clouds\":{\"all\":8},\"dt\":1502457955,\"sys\":{\"message\":0.0025,\"country\":\"RU\",\"sunrise\":1502416221,\"sunset\":1502470031},\"id\":484646,\"name\":\"Tambov\",\"cod\":200}", CurrentWeather.class);
        WeatherType weatherType = WeatherDataConverter.extractWeatherType(currentWeather.getWeather());
        assertEquals(weatherType, SUN);
    }

    private void initCurrentWeatherFavorites() {
        CityData cityData = new CityData.Builder(0, 0, 0L).build();
        currentWeatherFavorites = new CurrentWeatherFavorites.Builder(20, cityData, FAHRENHEIT)
                .weatherType(SNOW)
                .description("Clear")
                .humidity(20)
                .pressure(700)
                .windSpeed(2)
                .build();
    }

    private void initForecastWeather() {
        forecastWeatherElement = new ForecastWeatherElement.Builder("today", 20, 18, CELSIUS)
                .description("Clear")
                .windSpeed(3)
                .humidity(30)
                .pressure(700)
                .build();
    }
}
