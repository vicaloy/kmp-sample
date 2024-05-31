package com.events.infra.graphql.user

import com.events.domain.repository.UserRepository
import com.events.infra.dto.UserDTO
import com.events.infra.dto.UserMapper
import com.expediagroup.graphql.server.operations.Query

class UserQuery(private val userRepository: UserRepository) : Query {
    /*query {
        getAllUsers{
            id
            name
        }
    }*/
    fun getAllUsers(): List<UserDTO> = userRepository.getAll().map { UserMapper.toDTO(it)}

    /*query {
        findUser(id: 1){
            name
            email
        }
    }*/
    fun findUser(id: Int): UserDTO? = userRepository.find(id)?.let { UserMapper.toDTO(it)}

    fun filterUsers(name: String? = null, email: String? = null): List<UserDTO> =
        userRepository.filter(name, email).map { UserMapper.toDTO(it) }
}