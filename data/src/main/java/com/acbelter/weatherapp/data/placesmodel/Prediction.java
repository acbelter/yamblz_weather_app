package com.acbelter.weatherapp.data.placesmodel;

import android.support.annotation.VisibleForTesting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Prediction {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("matched_substrings")
    @Expose
    private List<MatchedSubstring> matchedSubstrings = new ArrayList<>();
    @SerializedName("place_id")
    @Expose
    private String placeId;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("structured_formatting")
    @Expose
    private StructuredFormatting structuredFormatting;
    @SerializedName("terms")
    @Expose
    private List<Term> terms = new ArrayList<>();
    @SerializedName("types")
    @Expose
    private List<String> types = new ArrayList<>();

    public String getDescription() {
        return description;
    }

    @VisibleForTesting
    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public List<MatchedSubstring> getMatchedSubstrings() {
        return matchedSubstrings;
    }

    public String getPlaceId() {
        return placeId;
    }

    @VisibleForTesting
    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getReference() {
        return reference;
    }

    public StructuredFormatting getStructuredFormatting() {
        return structuredFormatting;
    }

    public List<Term> getTerms() {
        return terms;
    }

    public List<String> getTypes() {
        return types;
    }

}
