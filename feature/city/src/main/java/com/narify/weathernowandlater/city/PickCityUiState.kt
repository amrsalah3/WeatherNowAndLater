package com.narify.weathernowandlater.city

import com.narify.weathernowandlater.core.presentation.ErrorState
import com.narify.weathernowandlater.domain.city.model.City

internal data class PickCityUiState(
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val cities: List<City> = emptyList(),
    val errorState: ErrorState = ErrorState()
)

