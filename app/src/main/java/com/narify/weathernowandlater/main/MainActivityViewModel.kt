package com.narify.weathernowandlater.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.narify.weathernowandlater.domain.city.usecase.GetUserSavedCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    getUserSavedCityUseCase: GetUserSavedCityUseCase
) : ViewModel() {

    internal val isUserCityStored: StateFlow<MainActivityState> = getUserSavedCityUseCase()
        .map { MainActivityState(hasFoundStoredCity = true) }
        .catch { emit(MainActivityState(hasFoundStoredCity = false)) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = MainActivityState(isLoading = true)
        )
}
