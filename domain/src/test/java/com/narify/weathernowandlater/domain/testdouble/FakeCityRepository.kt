package com.narify.weathernowandlater.domain.testdouble

import com.narify.weathernowandlater.domain.city.model.City
import com.narify.weathernowandlater.domain.city.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class FakeCityRepository : CityRepository {

    private val cities = listOf(
        City(
            name = "Cairo",
            fullLocation = "Cairo, Egypt",
            latitude = 30.0,
            longitude = 31.0,
        ),
        City(
            name = "Alexandria",
            fullLocation = "Alexandria, Egypt",
            latitude = 30.0,
            longitude = 29.0,
        )
    )

    private var savedCity: City? = null

    override suspend fun getCities(name: String): List<City> =
        cities.filter { it.name.contains(name, ignoreCase = true) }


    override suspend fun saveCity(city: City) {
        savedCity = city
    }

    override fun getSavedCity(): Flow<City> = flowOf(savedCity!!)
}
