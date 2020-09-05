package com.waxym.sampleandroidapp.factory

import com.waxym.sampleandroidapp.model.User
import com.waxym.sampleandroidapp.network.dtos.UserDto
import com.waxym.sampleandroidapp.ui.uio.UserUio

object UserFactory {
    fun buildUser(data: UserDto) = User(
        id = data.id,
        name = data.name,
        username = data.username,
        email = data.email,
        street = data.address?.street,
        city = data.address?.city,
        zipcode = data.address?.zipcode
    )

    fun buildUser(data: User) = UserUio(
        id = data.id,
        name = data.name,
        username = data.username
    )
}