package com.example.weatherapp.view;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.*;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.R;
//import com.example.weatherapp.model.WeatherData;
import com.example.weatherapp.model.WeatherData;
import com.example.weatherapp.viewmodel.WeatherViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button searchButton;
    private EditText zipCodeString;
    private RecyclerView recycleView;
    private WeatherViewModel weatherViewModel;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        searchButton = findViewById(R.id.searchButton);
        zipCodeString = findViewById(R.id.zipCode);
        recycleView = findViewById(R.id.recycleView);
        searchButton.setOnClickListener(this);
        layoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(layoutManager);

        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        weatherViewModel.getWeatherData().observe(this, new Observer<WeatherData>() {
                    @Override
                    public void onChanged(WeatherData weatherData) {
                        if(weatherData == null) {
                            Toast.makeText(HomeActivity.this, R.string.no_internet, Toast.LENGTH_SHORT).show();
                        }else {
                            setupRecyclerView(weatherData);
                        }
                    }
                }
                );
    }

    private void setupRecyclerView(WeatherData data) {
        List<WeatherData> list = new ArrayList<>();
        if (mAdapter == null) {
            mAdapter = new CustomAdapter(this, list);
            recycleView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.searchButton:
                TextView tv = findViewById(R.id.welcomeMessage);
                String code = zipCodeString.getText().toString();
                if(!code.isEmpty()) {
                    tv.setVisibility(View.GONE);
                    weatherViewModel.init(code);
                }else {
                    Toast.makeText(HomeActivity.this,R.string.no_zip_code,Toast.LENGTH_SHORT).show();
                }
        }
    }
}
