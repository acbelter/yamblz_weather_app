package com.acbelter.weatherapp.di.module;

import com.acbelter.weatherapp.data.network.LocationApi;
import com.acbelter.weatherapp.data.network.NetworkService;
import com.acbelter.weatherapp.data.network.NetworkServiceImpl;
import com.acbelter.weatherapp.data.network.PlacesApi;
import com.acbelter.weatherapp.data.network.WeatherApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(message -> Timber.d(message));
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return httpLoggingInterceptor;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    Retrofit.Builder provideRetrofitBuilder(OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    @Provides
    @Singleton
    WeatherApi provideWeatherApi(Retrofit.Builder builder) {
        return builder
                .baseUrl(WeatherApi.BASE_WEATHER_URL)
                .build()
                .create(WeatherApi.class);
    }

    @Provides
    @Singleton
    PlacesApi providePlacesApi(Retrofit.Builder builder) {
        return builder
                .baseUrl(PlacesApi.BASE_PLACES_URL)
                .build()
                .create(PlacesApi.class);
    }

    @Provides
    @Singleton
    LocationApi provideLocationApi(Retrofit.Builder builder) {
        return builder
                .baseUrl(LocationApi.BASE_LOCATION_URL)
                .build()
                .create(LocationApi.class);
    }

    @Provides
    @Singleton
    NetworkService provideNetworkService(WeatherApi weatherApi, PlacesApi placesApi, LocationApi locationApi) {
        return new NetworkServiceImpl(weatherApi, placesApi, locationApi);
    }
}