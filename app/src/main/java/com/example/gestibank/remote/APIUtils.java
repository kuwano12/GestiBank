package com.example.gestibank.remote;

public class APIUtils {
    public APIUtils(){}
    public static final String API_URL = "https://api.exchangeratesapi.io";
    public static final String MONGO_URL = "http://192.168.1.16:85";


    public static final RetrofitInterface getDeviceInterface(){
        return RetrofitClient.getClient(API_URL).create(RetrofitInterface.class);
    }
    public static final RetrofitInterface getuserInterface(){
        return RetrofitClient.getClient(MONGO_URL).create(RetrofitInterface.class);
    }
}
