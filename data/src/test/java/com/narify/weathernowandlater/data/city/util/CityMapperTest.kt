package com.narify.weathernowandlater.data.city.util

import com.narify.weathernowandlater.data.city.model.CityDto
import com.narify.weathernowandlater.domain.city.model.City
import org.junit.Test
import kotlin.test.assertEquals

internal class CityMapperTest {

    @Test
    fun `test mapping city DTO to city when only city and country is present`() {
        val cityDto = CityDto(
            name = "Cairo",
            country = "Egypt",
            lat = 30.0,
            lon = 31.0
        )
        val expectedCity = City(
            name = "Cairo",
            fullLocation = "Cairo, Egypt",
            latitude = 30.0,
            longitude = 31.0
        )

        val actualCity = cityDto.asCity()

        assertEquals(expectedCity, actualCity)
    }

    @Test
    fun `test mapping city DTO to city when city and state and country is present`() {
        val cityDto = CityDto(
            name = "London",
            state = "England",
            country = "United Kingdom",
            lat = 51.0,
            lon = 0.1,
        )
        val expectedCity = City(
            name = "London",
            fullLocation = "London, England, United Kingdom",
            latitude = 51.0,
            longitude = 0.1
        )

        val actualCity = cityDto.asCity()

        assertEquals(expectedCity, actualCity)
    }

    @Test
    fun `test mapping list of city DTOs to list of cities should return only valid cities`() {
        val cityDtos = listOf(
            CityDto(
                name = "Cairo",
                country = "Egypt",
                lat = 30.0,
                lon = 31.0
            ),
            CityDto(
                name = null,
                state = "England",
                country = "United Kingdom",
                lat = 51.0,
                lon = 0.1,
            )
        )
        val expectedCities = listOf(
            City(
                name = "Cairo",
                fullLocation = "Cairo, Egypt",
                latitude = 30.0,
                longitude = 31.0
            )
        )

        val actualCities = cityDtos.asCityList()

        assertEquals(expectedCities, actualCities)
    }
}
