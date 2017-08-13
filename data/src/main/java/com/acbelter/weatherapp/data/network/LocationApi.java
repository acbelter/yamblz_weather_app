package com.acbelter.weatherapp.data.network;

import android.support.annotation.WorkerThread;

import com.acbelter.weatherapp.data.locationmodel.Location;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.acbelter.weatherapp.data.BuildConfig.GOOGLE_PLACES_API_KEY;

public interface LocationApi {

    String BASE_LOCATION_URL = "https://maps.googleapis.com/maps/api/place/details/";

    @GET("json?key=" + GOOGLE_PLACES_API_KEY)
    @WorkerThread
    Single<Location> getLocation(@Query("placeid") String placeId,
                                 @Query("language") String language);
}
