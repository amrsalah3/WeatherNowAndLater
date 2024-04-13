package com.narify.weathernowandlater.data.city.testdouble

import com.narify.weathernowandlater.data.city.datasource.CityRemoteDataSource
import com.narify.weathernowandlater.data.city.model.CityDto

internal class FakeCityRemoteDataSource : CityRemoteDataSource {

    private val cityDtos = listOf(
        CityDto(
            name = "Cairo",
            country = "Egypt",
            lat = 30.0,
            lon = 31.0,
        ),
        CityDto(
            name = "Alexandria",
            country = "Egypt",
            lat = 29.0,
            lon = 31.0,
        ),
        CityDto(
            name = "New York City",
            state = "New York",
            country = "United States",
            lat = 25.0,
            lon = 20.0,
        ),
        CityDto(
            name = "London",
            state = "England",
            country = "United Kingdom",
            lat = 35.0,
            lon = -3.0,
        ),
        CityDto(
            name = "London",
            state = "Ontario",
            country = "Canada",
            lat = 35.0,
            lon = -3.0,
        ),
        CityDto(
            name = "Tokyo",
            country = "Japan",
            lat = 8.0,
            lon = -4.0,
        ),
        CityDto(
            name = "Sydney",
            state = "New South Wales",
            country = "Australia",
            lat = 21.0,
            lon = 24.0,
        )
    )

    override suspend fun getCities(name: String, limit: Int): List<CityDto> {
        val filteredCities = cityDtos.filter {
            it.name?.contains(name, ignoreCase = true) ?: false
        }
        val numberOfCities = limit.coerceAtMost(filteredCities.size)
        return filteredCities.take(numberOfCities)
    }
}
