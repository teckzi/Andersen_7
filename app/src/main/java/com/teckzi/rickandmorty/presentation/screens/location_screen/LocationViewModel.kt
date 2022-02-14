package com.teckzi.rickandmorty.presentation.screens.location_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.teckzi.domain.model.LocationModel
import com.teckzi.domain.use_cases.UseCases
import com.teckzi.rickandmorty.di.Injector
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _getAllLocations = useCases.getAllLocationUseCase()
    val getAllLocations = _getAllLocations

    private val _searchLocation = MutableStateFlow<PagingData<LocationModel>>(PagingData.empty())
    val searchLocation = _searchLocation

    fun searchLocation(
        name: String?,
        type: String?,
        dimension: String?
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.getSearchedLocationUseCase(
                name = name,
                type = type,
                dimension = dimension
            ).cachedIn(viewModelScope).collect {
                _searchLocation.value = it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Injector.clearLocationFragmentComponent()
    }
}