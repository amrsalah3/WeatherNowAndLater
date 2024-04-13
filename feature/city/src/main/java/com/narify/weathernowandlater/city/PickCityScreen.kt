package com.narify.weathernowandlater.city

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.narify.weathernowandlater.core.presentation.EmptyContent
import com.narify.weathernowandlater.core.presentation.LoadingContent
import com.narify.weathernowandlater.domain.city.model.City

@Composable
fun PickCityRoute(viewModel: PickCityViewModel = hiltViewModel(), onCitySelected: () -> Unit) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PickCityScreen(
        uiState = uiState,
        onSearchCity = viewModel::searchForCities,
        onSelectCity = {
            viewModel.selectCity(it)
            onCitySelected()
        }
    )
}

@Composable
private fun PickCityScreen(
    uiState: PickCityUiState,
    onSearchCity: (String) -> Unit,
    onSelectCity: (City) -> Unit
) {
    LazyColumn(Modifier.fillMaxSize()) {
        item {
            SearchBar(
                onSearch = onSearchCity,
                modifier = Modifier
                    .padding(vertical = 24.dp, horizontal = 16.dp)
                    .fillMaxWidth()
            )
        }

        if (uiState.isLoading) {
            item { LoadingContent(Modifier.fillParentMaxSize()) }
        } else if (uiState.errorState.hasError) {
            item {
                EmptyContent(
                    message = uiState.errorState.message,
                    modifier = Modifier.fillParentMaxSize()
                )
            }
        } else if (uiState.cities.isEmpty() && uiState.searchQuery.isNotBlank()) {
            item {
                EmptyContent(
                    message = "No matching cities found",
                    modifier = Modifier.fillParentMaxSize()
                )
            }
        } else {
            items(uiState.cities.size) { i ->
                CityItem(city = uiState.cities[i], onSelectCity = onSelectCity)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBar(
    onSearch: (query: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var query by rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        value = query,
        onValueChange = {
            query = it
            onSearch(query)
        },
        label = { Text("Search for your city") },
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
    )
}

@Composable
private fun CityItem(
    city: City,
    onSelectCity: (City) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier.clickable { onSelectCity(city) }) {
        Text(
            text = city.fullLocation,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun PickCityScreenPreview() {
    Surface {
        val uiState = PickCityUiState(
            cities = listOf(
                City(
                    name = "Cairo",
                    fullLocation = "Cairo, Egypt",
                    latitude = 30.0444,
                    longitude = 31.2357
                ),
                City(
                    name = "Alexandria",
                    fullLocation = "Alexandria, Egypt",
                    latitude = 31.2,
                    longitude = 29.9187
                ),
            )
        )
        PickCityScreen(uiState = uiState, onSearchCity = {}, onSelectCity = {})
    }
}
