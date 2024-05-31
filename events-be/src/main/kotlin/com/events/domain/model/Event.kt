package com.events.domain.model

open class Event(
    val id: Int,
    val title: String,
    val description: String,
    val timestamp: String,
    val place: Place,
    val organizer: User,
    val attendees: List<User>
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Event

        if (id != other.id) return false
        if (title != other.title) return false
        if (description != other.description) return false
        if (timestamp != other.timestamp) return false
        if (place != other.place) return false
        if (organizer != other.organizer) return false
        return attendees == other.attendees
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + timestamp.hashCode()
        result = 31 * result + place.hashCode()
        result = 31 * result + organizer.hashCode()
        result = 31 * result + attendees.hashCode()
        return result
    }
}