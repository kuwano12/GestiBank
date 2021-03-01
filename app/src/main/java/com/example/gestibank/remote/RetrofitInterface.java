package com.example.gestibank.remote;

import com.example.gestibank.model.Devise;
import com.example.gestibank.model.DeviseList;
import com.example.gestibank.model.Rate;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {
    @GET("/latest")
    Call<Devise> getDevices();
}
