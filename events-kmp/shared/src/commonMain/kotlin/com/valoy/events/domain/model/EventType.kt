package com.valoy.events.domain.model

enum class EventType(val value: String) {
    MUSICAL(MusicalEvent::class.simpleName.toString()),
    POLITICIAN(SportEvent::class.simpleName.toString()),
    OTHER(Event::class.simpleName.toString())
}