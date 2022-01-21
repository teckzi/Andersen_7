package com.teckzi.rickandmorty.presentation.screens.character_detail_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teckzi.rickandmorty.domain.model.CharacterModel
import com.teckzi.rickandmorty.domain.model.EpisodeModel
import com.teckzi.rickandmorty.domain.model.LocationModel
import com.teckzi.rickandmorty.domain.use_cases.UseCases
import com.teckzi.rickandmorty.util.Constants.CHARACTER_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedCharacter: MutableStateFlow<CharacterModel?> = MutableStateFlow(null)
    val selectedCharacter: StateFlow<CharacterModel?> = _selectedCharacter
    private val _episodeList: MutableStateFlow<List<EpisodeModel?>> = MutableStateFlow(emptyList())
    val episodeList: StateFlow<List<EpisodeModel?>> = _episodeList
    private val _characterOrigin: MutableStateFlow<LocationModel?> = MutableStateFlow(null)
    val characterOrigin: StateFlow<LocationModel?> = _characterOrigin
    private val _characterLocation: MutableStateFlow<LocationModel?> = MutableStateFlow(null)
    val characterLocation: StateFlow<LocationModel?> = _characterLocation

    init {
        val characterId = savedStateHandle.get<Int>(CHARACTER_ARGUMENT_KEY)
        viewModelScope.launch(Dispatchers.IO) {
            _selectedCharacter.value =
                characterId?.let { useCases.getSelectedCharacterUseCase(characterId = characterId) }

            val listOfEpisodeIds = _selectedCharacter.value!!.episode
            _episodeList.value = listOfEpisodeIds.let {
                useCases.getEpisodeListById(episodeIdList = listOfEpisodeIds)
            }
            val origin = _selectedCharacter.value!!.origin
            val location = _selectedCharacter.value!!.location
            if (origin != "unknown") _characterOrigin.value =
                useCases.getLocationByName(locationName = origin)
            if (location != "unknown") _characterLocation.value =
                useCases.getLocationByName(locationName = location)
        }
    }
}
