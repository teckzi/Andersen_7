package com.teckzi.rickandmorty.presentation.screens.location_detail_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teckzi.rickandmorty.domain.model.CharacterModel
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
    private val _characterList: MutableStateFlow<List<CharacterModel?>> =
        MutableStateFlow(emptyList())
    val characterList: StateFlow<List<CharacterModel?>> = _characterList

    init {
        val locationId = savedStateHandle.get<Int>(LOCATION_ARGUMENT_KEY)
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
}