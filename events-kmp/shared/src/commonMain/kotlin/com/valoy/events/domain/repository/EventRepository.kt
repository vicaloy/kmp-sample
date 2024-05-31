package com.valoy.events.domain.repository

import com.valoy.events.domain.model.Event
import com.valoy.events.domain.model.EventType
import com.valoy.events.domain.model.Place
import com.valoy.events.domain.model.User

interface EventRepository {
    fun add(event: Event): Boolean
    fun getAll(): List<Event>
    fun find(id: Int): Event?
    fun upsert(event: Event): Boolean
    fun delete(event: Event): Boolean
    fun filter(
        title: String?,
        description: String?,
        timestamp: String?,
        place: Place?,
        organizer: User?,
        type: EventType?
    ): List<Event>
}
