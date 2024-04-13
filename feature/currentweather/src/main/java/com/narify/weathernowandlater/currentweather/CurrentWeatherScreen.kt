package com.narify.weathernowandlater.currentweather

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
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
import com.narify.weathernowandlater.domain.weather.model.Weather
import com.narify.weathernowandlater.weatherdatatools.WeatherTextFormatter

@Composable
fun CurrentWeatherRoute(
    viewModel: CurrentWeatherViewModel = hiltViewModel(),
    onDailyForecastClicked: () -> Unit,
    onChangeCityClicked: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CurrentWeatherScreen(
        uiState = uiState,
        onDailyForecastClicked = onDailyForecastClicked,
        onChangeCityClicked = onChangeCityClicked
    )
}

@Composable
private fun CurrentWeatherScreen(
    uiState: CurrentWeatherUiState,
    onDailyForecastClicked: () -> Unit,
    onChangeCityClicked: () -> Unit
) {
    if (uiState.isLoading) {
        LoadingContent()
    } else if (uiState.errorState.hasError) {
        EmptyContent(uiState.errorState.message)
    } else {
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            WeatherTopBar(title = "Today's Weather", onChangeCityClicked = onChangeCityClicked)
            WeatherData(
                weather = uiState.weather,
                onDailyForecastClicked = onDailyForecastClicked
            )
        }
    }

}

@Composable
private fun WeatherData(
    weather: Weather,
    onDailyForecastClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
    ) {
        AsyncImage(
            model = weather.iconUrl,
            placeholder = debugPainterPlaceHolder(R.drawable.ic_weather_condition_preview),
            contentDescription = "Weather condition icon",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(200.dp)
        )

        Card(Modifier.fillMaxWidth(0.8f)) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Today")

                Text(
                    text = WeatherTextFormatter.addCelsiusWithIntTemperature(weather.temperature),
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.padding(vertical = 16.dp)
                )

                Text(
                    text = weather.condition,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                InfoItem(
                    iconRes = R.drawable.ic_wind_speed,
                    label = "Wind",
                    value = WeatherTextFormatter.addMPSWithIntWindSpeed(weather.windSpeed)
                )

                InfoItem(
                    iconRes = R.drawable.ic_humidity,
                    label = "Hum",
                    value = WeatherTextFormatter.addPercentageWithIntHumidity(weather.humidity)
                )
            }
        }

        Button(
            onClick = onDailyForecastClicked,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.padding(64.dp)
        ) {
            Text(text = "Show daily forecast")
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Show daily forecast",
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
private fun InfoItem(@DrawableRes iconRes: Int, label: String, value: String) {
    Row(
        Modifier
            .padding(bottom = 12.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = null,
                modifier = Modifier.padding(end = 16.dp)
            )
            Text(
                text = label,
                modifier = Modifier.padding(end = 16.dp)
            )
        }

        Text(text = "|")

        Text(
            text = value,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CurrentWeatherScreenPreview() {
    WeatherNowAndLaterTheme {
        Surface {
            val weather = Weather(
                day = "Friday",
                temperature = 30.0,
                humidity = 80.0,
                windSpeed = 20.0,
                condition = "Clear",
                description = "clear sky",
                iconUrl = "https://anylink.png"
            )
            CurrentWeatherScreen(
                uiState = CurrentWeatherUiState(weather = weather),
                onDailyForecastClicked = {},
                onChangeCityClicked = {}
            )
        }
    }
}
