package com.events.domain.repository

import com.events.domain.model.User

interface UserRepository {
    fun add(user: User): Boolean
    fun getAll(): List<User>
    fun find(id: Int): User?
    fun upsert(user: User): Boolean
    fun delete(user: User): Boolean
    fun filter(name: String?, email: String?): List<User>
}