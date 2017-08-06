package com.acbelter.weatherapp.domain.model.city;

public class CityData {

    private String shortName;
    private double latitude;
    private double longitude;

    public CityData() {

    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLongitude() {
        return longitude;
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
        return result;
    }

    @Override
    public String toString() {
        return "(shortName = " + shortName +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ")";
    }
}
