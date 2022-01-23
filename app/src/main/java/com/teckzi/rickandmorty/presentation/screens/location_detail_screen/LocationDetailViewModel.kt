package com.teckzi.rickandmorty.presentation.screens.location_detail_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teckzi.rickandmorty.di.Injector
import com.teckzi.rickandmorty.domain.model.CharacterModel
import com.teckzi.rickandmorty.domain.model.LocationModel
import com.teckzi.rickandmorty.domain.use_cases.UseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationDetailViewModel @Inject constructor(
    private val useCases: UseCases,
    //savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedLocation: MutableStateFlow<LocationModel?> = MutableStateFlow(null)
    val selectedLocation: StateFlow<LocationModel?> = _selectedLocation
    private val _characterList: MutableStateFlow<List<CharacterModel?>> =
        MutableStateFlow(emptyList())
    val characterList: StateFlow<List<CharacterModel?>> = _characterList

    init {
        //val locationId = savedStateHandle.get<Int>(LOCATION_ARGUMENT_KEY)
        val locationId = 5
        if (locationId != null) {
            viewModelScope.launch(Dispatchers.IO) {
                _selectedLocation.value =
                    locationId.let { useCases.getSelectedLocationUseCase(locationId = locationId) }

                val listOfCharacterIds = _selectedLocation.value!!.characters
                _characterList.value = listOfCharacterIds.let {
                    useCases.getCharacterListById(characterIdList = it)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Injector.clearLocationDetailComponent()
    }
}