package com.acbelter.weatherapp.data.network;

import com.acbelter.weatherapp.data.netmodel.NetworkWeatherData;
import com.acbelter.weatherapp.domain.model.WeatherParams;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import timber.log.Timber;

public class RetrofitNetworkService implements NetworkService {
    private Api mApi;

    public RetrofitNetworkService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(provideOkHttpClient())
                .addConverterFactory(provideConverterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mApi = retrofit.create(Api.class);
    }

    private static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(provideHttpLoggingInterceptor())
                .build();
    }

    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(message -> Timber.d(message));
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return httpLoggingInterceptor;
    }

    private static Converter.Factory provideConverterFactory() {
        return ScalarsConverterFactory.create();
    }

    @Override
    public Observable<NetworkWeatherData> getCurrentWeather(WeatherParams params) {
        return mApi.getCurrentWeatherData(params.getCity(), params.getLangCode()).map(data -> {
            JsonParser parser = new JsonParser();
            Gson gson = new Gson();
            JsonObject rootObject = parser.parse(data).getAsJsonObject();
            if (!rootObject.has("list")) {
                // If data has one weather condition, return it
                return gson.fromJson(rootObject, NetworkWeatherData.class);
            } else {
                // If data has several weather conditions, return first condition
                return gson.fromJson(rootObject.getAsJsonArray("list").get(0), NetworkWeatherData.class);
            }
        });
    }
}
