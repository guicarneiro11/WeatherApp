package com.guicarneirodev.weatherapp.util;

import com.guicarneirodev.weatherapp.R;

import java.util.Locale;

public class WeatherUtils {
    public static String formatTemperature(double temp) {
        return String.format(Locale.getDefault(), "%.1f°C", temp);
    }

    public static String formatWindSpeed(double speed) {
        return String.format(Locale.getDefault(), "%.1f km/h", speed);
    }

    public static int getWeatherIconResource(String iconCode) {
        return R.drawable.cloud;
    }
}
