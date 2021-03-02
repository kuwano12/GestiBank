package com.example.gestibank.remote;

import com.example.gestibank.model.Client;
import com.example.gestibank.model.Devise;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @GET("/latest")
    Call<Devise> getDevices();

    @POST("/user")
    Call<Client> addClient(@Body Client client);

    @POST("/login")
    Call<Client> login(@Body HashMap<String, String>map);
}
