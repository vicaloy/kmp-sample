package com.events.infra.repository

import com.events.domain.model.User
import com.events.domain.repository.UserRepository

object UserMemoryRepository : UserRepository {

    private val users: MutableList<User> = mutableListOf(User(id=1, name="name", lastname="lastname", email="email"))

    override fun add(user: User): Boolean {
        users.add(user)
        return true
    }

    override fun getAll(): List<User> = users

    override fun find(id: Int): User? = users.firstOrNull { user -> user.id == id }

    override fun upsert(user: User): Boolean {
        find(user.id)?.let { user -> users.remove(user) }
        users.add(user)
        return true
    }

    override fun delete(user: User): Boolean {
        users.remove(user)
        return true
    }

    override fun filter(name: String?, email: String?): List<User> {
        return users.filter { user ->
            name?.let { user.name.lowercase().contains(it.lowercase()) } == true || email?.let {
                user.email.lowercase().contains(
                    it.lowercase()
                )
            } == true
        }
    }
}