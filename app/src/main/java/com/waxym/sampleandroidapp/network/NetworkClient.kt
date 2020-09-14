package com.waxym.sampleandroidapp.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkClient {
    private const val JSON_PLACE_HOLDER_BASE_URL = "https://jsonplaceholder.typicode.com"
    private val gson = GsonBuilder()
        .setLenient()
        .create()
    private val retrofit = Retrofit.Builder()
        .baseUrl(JSON_PLACE_HOLDER_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val service: JsonPlaceHolderService = retrofit.create(JsonPlaceHolderService::class.java)
}