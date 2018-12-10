package com.example.dell.uasuser.REST;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "http://192.168.43.156:81/android_berita/";



    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        // Tambahan class untuk logging request/ response data dari REST
        // dapat dilihat di panel Logcat dengan tag Okhttp
        // https://stackoverflow.com/questions/45646188/how-can-i-debug-my-retrofit-api-call
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client) // untuk logging
                    .build();
        }
        return retrofit;
    }
}
