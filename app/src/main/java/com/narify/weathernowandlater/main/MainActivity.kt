package com.narify.weathernowandlater.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.narify.weathernowandlater.WeatherNowAndLaterDestinations.CURRENT_WEATHER_ROUTE
import com.narify.weathernowandlater.WeatherNowAndLaterDestinations.PICK_CITY_ROUTE
import com.narify.weathernowandlater.WeatherNowAndLaterNavGraph
import com.narify.weathernowandlater.core.presentation.theme.WeatherNowAndLaterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeatherNowAndLaterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val mainState by viewModel.isUserCityStored.collectAsState()
                    if (!mainState.isLoading) {
                        if (mainState.hasFoundStoredCity) {
                            WeatherNowAndLaterNavGraph(startDestination = CURRENT_WEATHER_ROUTE)
                        } else {
                            WeatherNowAndLaterNavGraph(startDestination = PICK_CITY_ROUTE)
                        }
                    }
                }
            }
        }
    }
}
