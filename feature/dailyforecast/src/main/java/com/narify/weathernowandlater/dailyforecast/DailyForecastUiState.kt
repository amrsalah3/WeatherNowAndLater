package com.narify.weathernowandlater.dailyforecast

import com.narify.weathernowandlater.domain.weather.model.Weather

internal sealed interface DailyForecastUiState {
    data object Loading : DailyForecastUiState
    data object Empty : DailyForecastUiState
    data class Forecast(val forecasts: List<Weather>) : DailyForecastUiState
    data class Error(val message: String) : DailyForecastUiState
}
