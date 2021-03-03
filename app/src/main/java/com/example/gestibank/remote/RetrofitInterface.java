package com.example.gestibank.remote;

import com.example.gestibank.model.User;
import com.example.gestibank.model.Devise;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitInterface {
    @GET("/latest")
    Call<Devise> getDevices();

    @POST("/user")
    Call<User> addClient(@Body User user);

    @GET("/login/{mail}")
    Call<User> login(@Path("mail") String mail);
}
