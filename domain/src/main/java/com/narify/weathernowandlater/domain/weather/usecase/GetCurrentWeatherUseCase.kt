package com.narify.weathernowandlater.domain.weather.usecase

import com.narify.weathernowandlater.domain.city.usecase.GetUserSavedCityUseCase
import com.narify.weathernowandlater.domain.weather.model.Weather
import com.narify.weathernowandlater.domain.weather.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val getUserSavedCityUseCase: GetUserSavedCityUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    operator fun invoke(): Flow<Weather> {
        return getUserSavedCityUseCase()
            .map { city -> weatherRepository.getCurrentWeather(city.latitude, city.longitude) }
            .flowOn(dispatcher)
    }
}
