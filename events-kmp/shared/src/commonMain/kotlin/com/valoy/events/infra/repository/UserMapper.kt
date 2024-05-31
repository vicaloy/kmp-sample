package com.valoy.events.infra.repository

import com.valoy.events.GetAllUsersQuery
import com.valoy.events.domain.model.User

fun GetAllUsersQuery.GetAllUser.toUser() = User(
    id = id,
    name = name,
    lastname = lastname,
    email = email
)