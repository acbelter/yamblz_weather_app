package com.acbelter.weatherapp.data.locationmodel;

import android.support.annotation.VisibleForTesting;

import com.acbelter.weatherapp.data.network.ApiErrors;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Location {

    @SerializedName("html_attributions")
    @Expose
    private List<Object> htmlAttributions = null;
    @SerializedName("result")
    @Expose
    private Result result;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Object> getHtmlAttributions() {
        return htmlAttributions;
    }

    public Result getResult() {
        return result;
    }

    public String getStatus() {
        return status;
    }

    @VisibleForTesting
    public void setStatus(ApiErrors.PlacesApiErrors errorCode) {
        this.status = errorCode.getError();
    }

    @VisibleForTesting
    public void setResult(Result result) {
        this.result = result;
    }
}
