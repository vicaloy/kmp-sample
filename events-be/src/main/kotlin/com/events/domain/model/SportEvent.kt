package com.events.domain.model

class SportEvent(
    id: Int,
    title: String,
    description: String,
    timestamp: String,
    place: Place,
    organizer: User,
    attendees: List<User>,
    val type: String,
    val localTeam: String,
    val visitingTeam: String,
) : Event(id, title, description, timestamp, place, organizer, attendees) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as SportEvent

        if (type != other.type) return false
        if (localTeam != other.localTeam) return false
        return visitingTeam == other.visitingTeam
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + localTeam.hashCode()
        result = 31 * result + visitingTeam.hashCode()
        return result
    }
}