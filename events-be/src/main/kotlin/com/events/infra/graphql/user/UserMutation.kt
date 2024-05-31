package com.events.infra.graphql.user

import com.events.domain.repository.UserRepository
import com.events.infra.dto.UserDTO
import com.events.infra.dto.UserMapper
import com.expediagroup.graphql.server.operations.Mutation

class UserMutation(private val userRepository: UserRepository) : Mutation {
    /*mutation {
        addUser(user: { id: 1, name: "John", lastname: "lastname", email: "john@example.com" })
    }*/
    fun addUser(user: UserDTO): Boolean {
        return userRepository.add(UserMapper.toDomain(user))
    }

    /*mutation {
        upsertUser(user: { id: 2, name: "Jessica", lastname: "lastname", email: "jessi@example.com" })
    }*/
    fun upsertUser(user: UserDTO): Boolean  {
        return userRepository.upsert(UserMapper.toDomain(user))
    }

    /*mutation {
        deleteUser(user: { id: 3, name: "Vico", lastname: "lastname", email: "vico@example.com" })
    }*/
    fun deleteUser(user: UserDTO): Boolean  {
        return userRepository.delete(UserMapper.toDomain(user))
    }
}