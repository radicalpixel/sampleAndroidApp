package com.waxym.sampleandroidapp.network

import com.waxym.sampleandroidapp.network.dtos.UserDto
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceHolderService {
    @GET("users")
    fun getUsers(): Deferred<List<UserDto>>
}