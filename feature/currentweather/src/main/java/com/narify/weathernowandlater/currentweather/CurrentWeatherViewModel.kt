package com.narify.weathernowandlater.currentweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.narify.weathernowandlater.core.presentation.ErrorState
import com.narify.weathernowandlater.domain.weather.usecase.GetCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {

    internal val uiState: StateFlow<CurrentWeatherUiState> = getCurrentWeatherUseCase()
        .map { weather -> CurrentWeatherUiState(weather = weather) }
        .catch {
            val errorState = ErrorState(
                hasError = true,
                message = "Something went wrong"
            )
            CurrentWeatherUiState(errorState = errorState)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = CurrentWeatherUiState(isLoading = true)
        )
}
