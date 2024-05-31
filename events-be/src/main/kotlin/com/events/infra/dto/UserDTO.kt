package com.events.infra.dto

import com.events.domain.model.User
import com.expediagroup.graphql.generator.annotations.GraphQLDescription

@GraphQLDescription("Some type description")
data class UserDTO(
    val id: Int,
    val name: String,
    @Deprecated("will be removed in future versions") val lastname: String,
    val email: String,
)

object UserMapper {
    fun toDomain(userDTO: UserDTO) = User(userDTO.id, userDTO.name, userDTO.lastname, userDTO.email)

    fun toDTO(user: User) = UserDTO(user.id, user.name, user.lastname, user.email)
}