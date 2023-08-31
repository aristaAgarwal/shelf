package com.example.shelf.model

import com.google.gson.annotations.SerializedName

data class CountryDO(
    val access: String,
    val `data`: Data,
    val status: String,
    @SerializedName("status-code")val status_code: Int,
    val version: String
)