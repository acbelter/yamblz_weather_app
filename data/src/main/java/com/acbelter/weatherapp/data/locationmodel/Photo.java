package com.acbelter.weatherapp.data.locationmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class Photo {

    @SerializedName("height")
    @Expose
    private int height;
    @SerializedName("html_attributions")
    @Expose
    private List<String> htmlAttributions = null;
    @SerializedName("photo_reference")
    @Expose
    private String photoReference;
    @SerializedName("width")
    @Expose
    private int width;

    public int getHeight() {
        return height;
    }

    public List<String> getHtmlAttributions() {
        return htmlAttributions;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public int getWidth() {
        return width;
    }
}
