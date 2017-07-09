package com.acbelter.weatherapp.di.module;

import com.acbelter.weatherapp.data.network.NetworkService;
import com.acbelter.weatherapp.data.network.RetrofitNetworkService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {
    @Provides
    @Singleton
    NetworkService provideNetworkService() {
        return new RetrofitNetworkService();
    }
}