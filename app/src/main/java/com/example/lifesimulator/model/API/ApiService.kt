package com.example.lifesimulator.model.API

import retrofit2.http.GET
import retrofit2.Call

interface ApiService {
    @GET("{endpoint}")
    fun getQuote(@retrofit2.http.Path("endpoint") endpoint: String): Call<ApiReponse>
}