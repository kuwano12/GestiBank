package com.example.gestibank.remote;

import com.example.gestibank.model.User;
import com.example.gestibank.model.Devise;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitInterface {
    @GET("/latest")
    Call<Devise> getDevices();

    @GET("/user/{role}")
    Call<List<User>> getAgents(@Path("role") String role);

    @POST("/user")
    Call<User> addClient(@Body User user);
    @PUT("/user/agent/{mail}")
    Call<User> updateUser(@Path("mail") String mail, @Body User user);

    @PUT("/user/client/{mail}")
    Call<User>affectUser(@Path("mail") String mail, @Body User agent);

    @PUT("/user/client/activate/{mail}")
    Call<User>changeClientStatus(@Path("mail") String mail, @Body User client);

    @DELETE("/user/agent/{mail}/{matricule}")
    Call<User> deleteUser(@Path("mail") String mail, @Path("matricule") String matricule);

    @GET("/user/client/{activated}")
    Call<List<User>> getPendingAccount(@Path("activated") String activated);

    @POST("/login/{mail}/{password}")
    Call<User> login(@Path("mail") String mail, @Path("password") String password);


}
