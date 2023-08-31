package com.example.shelf.network

class RetrofitService {
    var getcountries = RetrofitBuilder.getInstance().create(ApiInterface::class.java)
    var getBooks = RetrofitBuilderbooks.getInstance().create(ApiInterface::class.java)
}