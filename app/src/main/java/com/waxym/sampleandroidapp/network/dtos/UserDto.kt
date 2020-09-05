package com.waxym.sampleandroidapp.network.dtos

data class UserDto(
    val id: Int?,
    val name: String?,
    val username: String?,
    val email: String?,
    val address: AddressDto?
)