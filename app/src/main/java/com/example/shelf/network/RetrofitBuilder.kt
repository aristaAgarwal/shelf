package com.example.shelf.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitBuilder {
    val baseUrl = "https://api.first.org/data/v1/"

    val client = OkHttpClient.Builder()
        .connectTimeout(1,TimeUnit.MINUTES)
        .writeTimeout(1,TimeUnit.MINUTES)
        .readTimeout(1,TimeUnit.MINUTES)
        .build()

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}