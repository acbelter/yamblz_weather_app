package com.acbelter.weatherapp.data.weathermodel.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("main")
    @Expose
    private String main;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("icon")
    @Expose
    private String icon;

    /**
     * @return The id
     */
    public int getId() {
        return id;
    }

    /**
     * @return The main
     */
    public String getMain() {
        return main;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return The icon
     */
    public String getIcon() {
        return icon;
    }

}
