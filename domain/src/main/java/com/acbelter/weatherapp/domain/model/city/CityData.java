package com.acbelter.weatherapp.domain.model.city;

import android.os.Parcel;
import android.os.Parcelable;

public class CityData implements Parcelable {

    private String formattedAddress;
    private double latitude;
    private double longitude;

    public CityData() {

    }

    protected CityData(Parcel in) {
        formattedAddress = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public static final Creator<CityData> CREATOR = new Creator<CityData>() {
        @Override
        public CityData createFromParcel(Parcel in) {
            return new CityData(in);
        }

        @Override
        public CityData[] newArray(int size) {
            return new CityData[size];
        }
    };

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getFormattedAddress() {
        return formattedAddress;
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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(formattedAddress);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CityData))
            return false;
        CityData cityData = (CityData) o;
        return cityData.formattedAddress.equals(formattedAddress);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (formattedAddress == null ? 0 : formattedAddress.hashCode());
        result = 31 * result + Double.valueOf(latitude).hashCode();
        result = 31 * result + Double.valueOf(longitude).hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "(formattedAddress=" + formattedAddress +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ")";
    }
}
