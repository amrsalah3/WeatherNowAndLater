package com.narify.weathernowandlater.data.city.testdouble

import com.narify.weathernowandlater.data.city.datasource.CityLocalDataSource
import com.narify.weathernowandlater.domain.city.model.City
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class FakeCityLocalDataSource : CityLocalDataSource {

    private var savedCity: City? = null

    override suspend fun saveCity(city: City) {
        savedCity = city
    }

    override fun getSavedCity(): Flow<City> = flowOf(savedCity!!)
}
