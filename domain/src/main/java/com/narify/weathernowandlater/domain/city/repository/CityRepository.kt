package com.narify.weathernowandlater.domain.city.repository

import com.narify.weathernowandlater.domain.city.model.City
import kotlinx.coroutines.flow.Flow

interface CityRepository {
    suspend fun getCities(name: String): List<City>
    suspend fun saveCity(city: City)
    fun getSavedCity(): Flow<City>
}
