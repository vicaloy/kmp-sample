package com.valoy.events.domain.model

data class Place(
    val id: Int,
    val eventId: Int,
    val street: String,
    val latitude: Double,
    val longitude: Double,
    val description: String
)