package com.waxym.sampleandroidapp.repository

import android.util.Log
import com.waxym.sampleandroidapp.factory.UserFactory
import com.waxym.sampleandroidapp.model.User
import com.waxym.sampleandroidapp.network.NetworkClient
import com.waxym.sampleandroidapp.network.Request

class UserRepository {
    suspend fun user(): Request<List<User>> {
        return try {
            val response = NetworkClient.service.getUsers()
            val data = response.map { UserFactory.buildUser(it) }
            Request(status = Request.Status.SUCCESS, data = data)
        } catch (error: Exception) {
            Log.e("UserRepository", "UserRepository#user() Exception occur !", error)
            Request(status = Request.Status.ERROR, error = error)
        }
    }
}