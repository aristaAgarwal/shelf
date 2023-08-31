package com.example.shelf.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier

object RetrofitBuilderbooks {
    val baseUrl = "https://jsonkeeper.com/b/"

    val client = OkHttpClient.Builder()
        .hostnameVerifier(HostnameVerifier { hostname, session -> true })
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