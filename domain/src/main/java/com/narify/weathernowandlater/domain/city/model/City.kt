package com.narify.weathernowandlater.domain.city.model

import kotlinx.serialization.Serializable

@Serializable
data class City(
    val name: String,
    val fullLocation: String,
    val latitude: Double,
    val longitude: Double,
)
