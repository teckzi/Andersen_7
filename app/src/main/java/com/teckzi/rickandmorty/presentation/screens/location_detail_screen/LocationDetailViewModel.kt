package com.teckzi.rickandmorty.presentation.screens.location_detail_screen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teckzi.rickandmorty.domain.model.LocationModel
import com.teckzi.rickandmorty.domain.use_cases.UseCases
import com.teckzi.rickandmorty.util.Constants.LOCATION_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedLocation: MutableStateFlow<LocationModel?> = MutableStateFlow(null)
    val selectedLocation: StateFlow<LocationModel?> = _selectedLocation

    init {
        val locationId = savedStateHandle.get<Int>(LOCATION_ARGUMENT_KEY)
        viewModelScope.launch(Dispatchers.IO) {
            _selectedLocation.value =
                locationId?.let { useCases.getSelectedLocationUseCase(locationId = locationId) }
        }
        Log.d("TAG location viewModel savedStateHandle", "$locationId")
    }
}