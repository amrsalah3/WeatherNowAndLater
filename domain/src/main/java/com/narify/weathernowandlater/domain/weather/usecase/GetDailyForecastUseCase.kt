package com.narify.weathernowandlater.domain.weather.usecase

import com.narify.weathernowandlater.domain.city.usecase.GetUserSavedCityUseCase
import com.narify.weathernowandlater.domain.weather.model.Weather
import com.narify.weathernowandlater.domain.weather.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetDailyForecastUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val getUserSavedCityUseCase: GetUserSavedCityUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(): List<Weather> = withContext(dispatcher) {
        val city = getUserSavedCityUseCase().first()
        return@withContext weatherRepository.getDailyForecast(city.latitude, city.longitude)
    }
}
