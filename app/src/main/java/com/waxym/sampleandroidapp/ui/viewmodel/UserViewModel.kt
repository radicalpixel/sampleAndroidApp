package com.waxym.sampleandroidapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.waxym.sampleandroidapp.factory.UserFactory
import com.waxym.sampleandroidapp.model.User
import com.waxym.sampleandroidapp.network.NetworkClient
import com.waxym.sampleandroidapp.network.Request
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val scope = CoroutineScope(Dispatchers.Default)

    private val _users = MutableLiveData<List<User>>(listOf())
    val users: LiveData<List<User>> get() = _users

    fun user(): LiveData<Request<List<User>>> {
        // build a new live data that allow to trace the call status, useful to display loaders.
        val callStatusLiveData = MutableLiveData<Request<List<User>>>(Request(Request.Status.FETCHING))
        scope.launch {
            val request: Request<List<User>> = try {
                val response = NetworkClient.service.getUsers().await()
                val data = response.map { UserFactory.buildUser(it) }
                // update UI value
                _users.postValue(data)
                Request(status = Request.Status.SUCCESS, data = data)
            } catch (error: Exception) {
                Log.e("UserViewModel", "UserViewModel#user() Exception rised !", error)
                Request(status = Request.Status.ERROR, error = error)
            }
            // update call status value with latest data.
            callStatusLiveData.postValue(request)
        }
        return callStatusLiveData
    }
}

