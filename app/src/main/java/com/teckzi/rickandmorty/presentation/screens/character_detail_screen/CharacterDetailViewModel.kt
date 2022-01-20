package com.teckzi.rickandmorty.presentation.screens.character_detail_screen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teckzi.rickandmorty.domain.model.CharacterModel
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

    init {
        val characterId = savedStateHandle.get<Int>(CHARACTER_ARGUMENT_KEY)
        viewModelScope.launch(Dispatchers.IO) {
            _selectedCharacter.value =
                characterId?.let { useCases.getSelectedCharacterUseCase(characterId = characterId) }
        }
        Log.d("TAG char viewModel savedStateHandle", "$characterId")
    }
}
