package com.narify.weathernowandlater.main

internal data class MainActivityState(
    val isLoading: Boolean = false,
    val hasFoundStoredCity: Boolean = false
)
