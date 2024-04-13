package com.narify.weathernowandlater.dailyforecast

internal sealed class DailyForecastUiEvent {
    object UpdateDailyForecast : DailyForecastUiEvent()
}
