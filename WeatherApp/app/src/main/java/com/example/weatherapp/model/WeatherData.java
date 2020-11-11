package com.example.weatherapp.model;

public class WeatherData {
    //private Clouds cloud;
    public String description;
    public String city;
    public String visibility;
    public Temperature temp;
   public WeatherData() {
        temp = new Temperature();
    }
}
