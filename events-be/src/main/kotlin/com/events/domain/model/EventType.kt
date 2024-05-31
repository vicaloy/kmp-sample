package com.events.domain.model

enum class EventType(val value: String) {
    MUSICAL(MusicalEvent::class.java.simpleName),
    POLITICIAN(SportEvent::class.java.simpleName),
    OTHER(Event::class.java.simpleName)
}