package com.teckzi.rickandmorty.presentation.screens.character_detail_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teckzi.rickandmorty.domain.model.CharacterModel
import com.teckzi.rickandmorty.domain.model.LocationModel
import com.teckzi.rickandmorty.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _selectedCharacter: MutableStateFlow<CharacterModel?> = MutableStateFlow(null)
    val selectedCharacter: StateFlow<CharacterModel?> = _selectedCharacter

    private val _selectedOrigin: MutableStateFlow<String?> = MutableStateFlow(null)
    val selectedOrigin: StateFlow<String?> = _selectedOrigin

    private val _selectedLocation: MutableStateFlow<String?> = MutableStateFlow(null)
    val selectedLocation: StateFlow<String?> = _selectedLocation

    fun getCharacter(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.getSelectedCharacterUseCase(characterId = id).let {
                _selectedCharacter.value = it
            }
        }
    }

    fun getOrigin(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.getSelectedLocationUseCase(locationId = id).let {
                _selectedOrigin.value = if (it == null) "unknown" else it.name
                Log.d("TAG char detail viewmodel","${_selectedOrigin.value}")
            }
        }
    }

    fun getLocation(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.getSelectedLocationUseCase(locationId = id).let {
                _selectedLocation.value = if (it == null) "unknown" else it.name
                Log.d("TAG char detail viewmodel","${_selectedOrigin.value}")
            }
        }
    }
}
