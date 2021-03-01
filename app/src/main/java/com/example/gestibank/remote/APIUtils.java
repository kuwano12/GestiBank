package com.example.gestibank.remote;

public class APIUtils {
    public APIUtils(){}
    public static final String API_URL = "https://api.exchangeratesapi.io";

    public static final RetrofitInterface getDeviceInterface(){
        return RetrofitClient.getClient(API_URL).create(RetrofitInterface.class);
    }
}
