package com.acbelter.weatherapp.di.module;

import com.acbelter.weatherapp.data.network.Api;
import com.acbelter.weatherapp.data.network.NetworkService;
import com.acbelter.weatherapp.data.network.NetworkServiceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
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
    Converter.Factory provideConverterFactory() {
        return ScalarsConverterFactory.create();
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
    Retrofit.Builder provideRetrofitBuilder(OkHttpClient client,
                                            Converter.Factory converter) {
        return new Retrofit.Builder()
                .client(client)
                .addConverterFactory(converter)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    @Provides
    @Singleton
    Api provideApi(Retrofit.Builder builder) {
        return builder
                .baseUrl(Api.BASE_URL)
                .build()
                .create(Api.class);
    }

    @Provides
    @Singleton
    NetworkService provideNetworkService(Api api) {
        return new NetworkServiceImpl(api);
    }
}