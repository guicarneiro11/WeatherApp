package com.guicarneirodev.weatherapp.presenter;

import com.guicarneirodev.weatherapp.model.Weather;

import java.util.List;

public class WeatherHistoryResponse {
    private List<Weather> data;
    private int count;

    public List<Weather> getData() {
        return data;
    }

    public void setData(List<Weather> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
