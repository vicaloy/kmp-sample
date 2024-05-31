package com.valoy.events.domain.repository

import com.valoy.events.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun add(user: User): Boolean
    suspend fun getAll(): Flow<List<User>>
    fun find(id: Int): User?
    fun upsert(user: User): Boolean
    fun delete(user: User): Boolean
    fun filter(name: String?, email: String?): List<User>
}