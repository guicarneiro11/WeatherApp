package com.guicarneirodev.weatherapp;

import android.app.Application;
import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class WeatherApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
