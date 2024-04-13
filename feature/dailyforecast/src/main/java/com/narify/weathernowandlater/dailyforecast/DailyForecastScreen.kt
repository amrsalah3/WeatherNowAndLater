package com.narify.weathernowandlater.dailyforecast

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.narify.weathernowandlater.core.R
import com.narify.weathernowandlater.core.presentation.EmptyContent
import com.narify.weathernowandlater.core.presentation.LoadingContent
import com.narify.weathernowandlater.core.presentation.WeatherTopBar
import com.narify.weathernowandlater.core.presentation.debugPainterPlaceHolder
import com.narify.weathernowandlater.core.presentation.theme.WeatherNowAndLaterTheme
import com.narify.weathernowandlater.core.util.DayFormatter
import com.narify.weathernowandlater.domain.weather.model.Weather


@Composable
fun DailyForecastRoute(
    viewModel: DailyForecastViewModel = hiltViewModel(),
    onChangeCityClicked: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    DailyForecastScreen(uiState = uiState, onChangeCityClicked = onChangeCityClicked)
}

@Composable
private fun DailyForecastScreen(
    uiState: DailyForecastUiState,
    onChangeCityClicked: () -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        WeatherTopBar(title = "Daily Forecast", onChangeCityClicked = onChangeCityClicked)
        when (uiState) {
            DailyForecastUiState.Loading -> LoadingContent()
            DailyForecastUiState.Empty -> EmptyContent("No available forecasts")
            is DailyForecastUiState.Forecast -> DailyForecastList(uiState.forecasts)
            is DailyForecastUiState.Error -> EmptyContent(uiState.message)
        }
    }
}

@Composable
private fun DailyForecastList(forecasts: List<Weather>, modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize(),
    ) {
        items(forecasts.size) { index ->
            ForecastItem(forecasts[index])
        }
    }
}

@Composable
private fun ForecastItem(forecast: Weather, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = forecast.iconUrl,
            placeholder = debugPainterPlaceHolder(R.drawable.ic_weather_condition_preview),
            contentDescription = "Weather condition icon",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .size(64.dp)
        )

        Text(text = forecast.day, modifier = Modifier.weight(1f))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(forecast.condition)
            Text("${forecast.temperature}°C")
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DailyForecastListPreview() {
    WeatherNowAndLaterTheme {
        Surface {
            val dailyForecast = List(7) { day ->
                Weather(
                    DayFormatter.getDayName(day + 1),
                    temperature = (10..30).random().toDouble(),
                    humidity = (30..80).random().toDouble(),
                    windSpeed = (5..50).random().toDouble(),
                    condition = "Clear",
                    description = "clear sky",
                    iconUrl = "https://anylink.png"
                )
            }

            DailyForecastScreen(
                DailyForecastUiState.Forecast(dailyForecast),
                onChangeCityClicked = {}
            )
        }
    }
}
