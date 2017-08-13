package com.acbelter.weatherapp.domain.model.city;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class CityData {

    @NonNull
    private double latitude;
    @NonNull
    private double longitude;
    @Nullable
    private String shortName;
    @NonNull
    private long timestamp;

    public static class Builder {
        //Requered params
        private final double latitude;
        private final double longitude;
        private final long timestamp;

        //Optional params
        private String shortName = "";

        public Builder(double latitude, double longitude, long timestamp) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.timestamp = timestamp;
        }

        public Builder shortName(String val) {
            shortName = val;
            return this;
        }

        public CityData build() {
            return new CityData(this);
        }
    }

    private CityData(Builder builder) {
        latitude = builder.latitude;
        longitude = builder.longitude;
        shortName = builder.shortName;
        timestamp = builder.timestamp;
    }

    @Nullable
    public String getShortName() {
        return shortName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CityData))
            return false;
        CityData cityData = (CityData) o;
        return (Double.compare(cityData.latitude, latitude) == 0)
                && (Double.compare(cityData.longitude, longitude) == 0);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (shortName == null ? 0 : shortName.hashCode());
        result = 31 * result + Double.valueOf(latitude).hashCode();
        result = 31 * result + Double.valueOf(longitude).hashCode();
        result = 31 * result + Long.valueOf(timestamp).hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "(shortName = " + shortName +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", timestamp=" + timestamp +
                ")";
    }
}
