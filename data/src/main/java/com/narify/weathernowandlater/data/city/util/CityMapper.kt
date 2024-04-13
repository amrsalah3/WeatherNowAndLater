package com.narify.weathernowandlater.data.city.util

import com.narify.weathernowandlater.data.city.model.CityDto
import com.narify.weathernowandlater.domain.city.model.City

// Convert city DTO from API model into city domain model
internal fun CityDto.asCity(): City {
    val state = if (!state.isNullOrBlank()) " ${state}," else ""
    return City(
        name = name!!,
        fullLocation = "${name},$state ${country ?: ""}",
        latitude = lat!!,
        longitude = lon!!
    )
}

// Convert the list of city DTOs from API model into a list of city domain model
internal fun List<CityDto>.asCityList(): List<City> {
    val cities = mutableListOf<City>()

    for (cityDto in this) {
        try {
            cities += cityDto.asCity()
        } catch (npe: NullPointerException) {
            continue
        }
    }

    return cities
}
