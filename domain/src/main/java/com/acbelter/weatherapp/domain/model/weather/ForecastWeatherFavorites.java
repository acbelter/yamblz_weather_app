package com.acbelter.weatherapp.domain.model.weather;

public class ForecastWeatherFavorites {

    private String date;
    private int maxTemp;
    private int minTemp;

    public static class Builder {
        //Requered params

        private final String date;
        private final int highTemperature;
        private final int lowTemperature;

        public Builder(String date, int highTemperature, int lowTemperature) {
            this.date = date;
            this.highTemperature = highTemperature;
            this.lowTemperature = lowTemperature;
        }

        public ForecastWeatherFavorites build() {
            return new ForecastWeatherFavorites(this);
        }
    }

    public ForecastWeatherFavorites(Builder builder) {
        date = builder.date;
        maxTemp = builder.highTemperature;
        minTemp = builder.lowTemperature;
    }

    public String getDate() {
        return date;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }
}
