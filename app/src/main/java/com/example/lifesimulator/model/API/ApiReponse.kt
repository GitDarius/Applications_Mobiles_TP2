package com.example.lifesimulator.model.API

import com.google.gson.annotations.SerializedName

data class ApiReponse(
    @SerializedName("results")
    val resultats: List<ApiImage>
)
