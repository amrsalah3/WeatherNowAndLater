package com.narify.weathernowandlater.currentweather

import com.narify.weathernowandlater.core.presentation.ErrorState
import com.narify.weathernowandlater.domain.weather.model.Weather

internal data class CurrentWeatherUiState(
    val isLoading: Boolean = false,
    val weather: Weather = Weather.empty(),
    val errorState: ErrorState = ErrorState()
)
