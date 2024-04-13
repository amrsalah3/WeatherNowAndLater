package com.narify.weathernowandlater

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.narify.weathernowandlater.WeatherNowAndLaterDestinations.CURRENT_WEATHER_ROUTE
import com.narify.weathernowandlater.WeatherNowAndLaterDestinations.DAILY_FORECAST_ROUTE
import com.narify.weathernowandlater.WeatherNowAndLaterDestinations.PICK_CITY_ROUTE
import com.narify.weathernowandlater.city.PickCityRoute
import com.narify.weathernowandlater.currentweather.CurrentWeatherRoute
import com.narify.weathernowandlater.dailyforecast.DailyForecastRoute

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WeatherNowAndLaterNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = CURRENT_WEATHER_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(PICK_CITY_ROUTE) {
            PickCityRoute(
                onCitySelected = {
                    navController.navigate(CURRENT_WEATHER_ROUTE) {
                        popUpTo(CURRENT_WEATHER_ROUTE) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(CURRENT_WEATHER_ROUTE) {
            CurrentWeatherRoute(
                onDailyForecastClicked = { navController.navigate(DAILY_FORECAST_ROUTE) },
                onChangeCityClicked = { navController.navigate(PICK_CITY_ROUTE) }
            )
        }

        composable(DAILY_FORECAST_ROUTE) {
            DailyForecastRoute(
                onChangeCityClicked = { navController.navigate(PICK_CITY_ROUTE) }
            )
        }
    }
}
