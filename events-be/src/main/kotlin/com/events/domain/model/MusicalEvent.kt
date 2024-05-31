package com.events.domain.model

class MusicalEvent(
    id: Int,
    title: String,
    description: String,
    timestamp: String,
    place: Place,
    organizer: User,
    attendees: List<User>,
    val artist: String,
    val genre: String,
    val ticketPrice: Double
) : Event(id, title, description, timestamp, place, organizer, attendees){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as MusicalEvent

        if (artist != other.artist) return false
        if (genre != other.genre) return false
        return ticketPrice == other.ticketPrice
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + artist.hashCode()
        result = 31 * result + genre.hashCode()
        result = 31 * result + ticketPrice.hashCode()
        return result
    }
}