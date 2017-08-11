package com.acbelter.weatherapp.data.placesmodel;

import android.support.annotation.VisibleForTesting;

import com.acbelter.weatherapp.data.network.ApiErrors;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Places {

    @SerializedName("predictions")
    @Expose
    private List<Prediction> predictions = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Prediction> getPredictions() {
        return predictions;
    }

    public String getStatus() {
        return status;
    }

    @VisibleForTesting
    public void setStatus(ApiErrors.PlacesApiErrors status) {
        this.status = status.getError();
    }

    @VisibleForTesting
    public void setPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
    }

}
