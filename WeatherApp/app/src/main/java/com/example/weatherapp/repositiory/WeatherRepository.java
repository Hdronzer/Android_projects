package com.example.weatherapp.repositiory;

import com.example.weatherapp.model.WeatherData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import androidx.lifecycle.MutableLiveData;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherRepository {

    private int ZipCode;
    private String Country;

    private OkHttpClient client;
    private static volatile WeatherRepository instance;

    private MutableLiveData<WeatherData> mutableData;

    private WeatherRepository() {
        client = new OkHttpClient();
        mutableData = new MutableLiveData<>();
    }
    public static WeatherRepository getInstance() {
        if(instance == null) {
            synchronized (WeatherRepository.class) {
                if(instance == null){
                    instance = new WeatherRepository();
                }
            }
        }
        return instance;
    }

    public MutableLiveData<WeatherData> getMutableData() {
        return mutableData;
    }

    public void updateData(String url) {
        getWebData(url);
    }
    private void getWebData(String url) {
        final Request request = new Request.Builder()
                                            .url(url)
                                            .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mutableData.postValue(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str_response = response.body().toString();
                try {
                    JSONObject reader = new JSONObject(str_response);
                    WeatherData wet = new WeatherData();
                    JSONArray weather = reader.getJSONArray("weather");
                    for (int i = 0; i < weather.length(); i++) {
                        JSONObject c = weather.getJSONObject(i);
                        wet.description = c.getString("description");
                    }
                    JSONObject temp = reader.getJSONObject("phone");
                    wet.temp.cTemp = temp.getString("temp");
                    wet.temp.minTemp = temp.getString("temp_min");
                    wet.temp.maxTemp = temp.getString("temp_max");
                    wet.temp.humidity = temp.getString("humidity");

                    wet.visibility = reader.getString("visibility");
                    wet.city = reader.getString("name");

                    mutableData.postValue(wet);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void parseJson(JSONObject reader) throws Exception {

    }
    public int getZipCode() {
        return ZipCode;
    }

    public void setZipCode(int zipCode) {
        ZipCode = zipCode;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

}
