package com.teckzi.rickandmorty.presentation.screens.character_detail_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teckzi.rickandmorty.domain.model.CharacterModel
import com.teckzi.rickandmorty.domain.use_cases.UseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "TAG CharacterDetailViewModel"

class CharacterDetailViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _selectedCharacter: MutableStateFlow<CharacterModel?> = MutableStateFlow(null)
    val selectedCharacter: StateFlow<CharacterModel?> = _selectedCharacter

    fun getCharacter(id: Int) {
        viewModelScope.launch {
            _selectedCharacter.value = useCases.getSelectedCharacterUseCase(characterId = id)
        }
    }
}
