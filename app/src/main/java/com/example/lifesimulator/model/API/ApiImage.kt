package com.example.lifesimulator.model.API

import com.google.gson.annotations.SerializedName

data class ApiImage(
    @SerializedName("url")
    val image: String,
)
