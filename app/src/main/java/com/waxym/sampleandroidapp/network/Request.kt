package com.waxym.sampleandroidapp.network

import java.lang.Exception

data class Request<T>(
    val status: Status,
    val data: T? = null,
    val error: Exception? = null
) {
    enum class Status {
        FETCHING,
        SUCCESS,
        ERROR
    }
}