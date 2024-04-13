package com.narify.weathernowandlater.core.presentation

data class ErrorState(
    val hasError: Boolean = false,
    val message: String = ""
)
