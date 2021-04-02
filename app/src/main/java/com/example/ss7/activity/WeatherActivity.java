package com.example.ss7.activity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ss7.R;
import com.example.ss7.adapter.WeatherAdapter;
import com.example.ss7.model.Weather;
import com.example.ss7.network.ApiManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherActivity extends AppCompatActivity {

    RecyclerView rvList;
    List<Weather> listData;
    WeatherAdapter adapter;
    TextView tvValue,tvStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = findViewById(R.id.tvStatus);
        tvValue = findViewById(R.id.tvValue);

        getListData();

        listData = new ArrayList<>();
        adapter = new WeatherAdapter(WeatherActivity.this,listData);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);

        rvList = findViewById(R.id.rvList);
        rvList.setLayoutManager(layoutManager);
        rvList.setAdapter(adapter);
    }

    private void getListData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiManager service = retrofit.create(ApiManager.class);
        service.getWeather().enqueue(new Callback<List<Weather>>() {
            @Override
            public void onResponse(Call<List<Weather>> call, Response<List<Weather>> response) {
                if (response.body() != null){
                    listData = response.body();
                    adapter.reloadData(listData);
                    tvStatus.setText(listData.get(0).getStatus());
                    tvValue.setText(String.valueOf(listData.get(0).getTemperature().getValue())+ "º");
                }
            }

            @Override
            public void onFailure(Call<List<Weather>> call, Throwable t) {
                Toast.makeText(WeatherActivity.this,"Fail",Toast.LENGTH_LONG).show();
            }
        });
    }

}
