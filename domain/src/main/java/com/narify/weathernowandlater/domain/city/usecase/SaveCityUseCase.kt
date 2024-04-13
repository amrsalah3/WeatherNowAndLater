package com.narify.weathernowandlater.domain.city.usecase

import com.narify.weathernowandlater.domain.city.model.City
import com.narify.weathernowandlater.domain.city.repository.CityRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveCityUseCase @Inject constructor(
    private val cityRepository: CityRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(city: City) = withContext(dispatcher) {
        return@withContext cityRepository.saveCity(city)
    }
}
