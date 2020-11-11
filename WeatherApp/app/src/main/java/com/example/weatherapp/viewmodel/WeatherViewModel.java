package com.example.weatherapp.viewmodel;

import com.example.weatherapp.model.WeatherData;
import com.example.weatherapp.repositiory.WeatherRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import static com.example.weatherapp.utils.Constants.ENDPOINT;
import static com.example.weatherapp.utils.Constants.COUNTRY_CODE;
import static com.example.weatherapp.utils.Constants.APP_ID;

public class WeatherViewModel extends ViewModel {
    private WeatherRepository repository;

    WeatherViewModel () {
        repository = WeatherRepository.getInstance();
    }

    public void init(String ZipCode) {
        String url = ENDPOINT + ZipCode + COUNTRY_CODE + APP_ID;
    }

    public LiveData<WeatherData> getWeatherData() {
        return repository.getMutableData();
    }
}
