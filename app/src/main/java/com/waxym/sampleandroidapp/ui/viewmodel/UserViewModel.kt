package com.waxym.sampleandroidapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.waxym.sampleandroidapp.factory.UserFactory
import com.waxym.sampleandroidapp.model.User
import com.waxym.sampleandroidapp.network.Request
import com.waxym.sampleandroidapp.repository.UserRepository
import com.waxym.sampleandroidapp.ui.uio.UserUio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val scope = CoroutineScope(Dispatchers.Default)

    private val userRepository = UserRepository()
    private val _users = MutableLiveData<List<UserUio>>(listOf())
    val users: LiveData<List<UserUio>> get() = _users

    fun user(): LiveData<Request<List<UserUio>>> {
        // build a new live data that allow to trace the call status, useful to display loaders.
        val callStatusLiveData = MutableLiveData<Request<List<UserUio>>>(Request(Request.Status.FETCHING))
        scope.launch {
            val request: Request<List<User>> = userRepository.user()
            // update UI.
            val data = request.data?.map { UserFactory.buildUser(it) }
            _users.postValue(data)
            // update call status value with latest data.
            callStatusLiveData.postValue(Request(status = Request.Status.SUCCESS, data))
        }
        return callStatusLiveData
    }
}

