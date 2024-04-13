package com.narify.weathernowandlater.domain.city.usecase

import com.narify.weathernowandlater.domain.city.model.City
import com.narify.weathernowandlater.domain.city.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserSavedCityUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {
    operator fun invoke(): Flow<City> = cityRepository.getSavedCity()
}
