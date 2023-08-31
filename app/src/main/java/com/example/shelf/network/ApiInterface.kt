package com.example.shelf.network

import com.example.shelf.ui.model.CountryDO
import com.example.shelf.ui.model.ShelfDO
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("countries")
    suspend fun getCountries(
    ):Response<CountryDO>

    @GET("ZEDF")
    suspend fun getBooks(
    ):Response<ShelfDO>
}