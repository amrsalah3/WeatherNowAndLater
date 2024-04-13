package com.narify.weathernowandlater.dailyforecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.narify.weathernowandlater.domain.weather.usecase.GetDailyForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyForecastViewModel @Inject constructor(
    private val getDailyForecastUseCase: GetDailyForecastUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<DailyForecastUiState>(DailyForecastUiState.Empty)
    internal val uiState = _uiState.asStateFlow()

    init {
        reduce(DailyForecastUiEvent.UpdateDailyForecast)
    }

    internal fun reduce(event: DailyForecastUiEvent) = viewModelScope.launch {
        when (event) {
            DailyForecastUiEvent.UpdateDailyForecast -> {
                _uiState.update { DailyForecastUiState.Loading }
                try {
                    val forecast = getDailyForecastUseCase()
                    _uiState.update { DailyForecastUiState.Forecast(forecast) }
                } catch (e: Exception) {
                    _uiState.update { DailyForecastUiState.Error("Something went wrong") }
                }
            }
        }
    }
}
