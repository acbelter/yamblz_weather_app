
package com.acbelter.weatherapp.data.netmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rain {
    @SerializedName("3h")
    @Expose
    public int volume3h;
}
