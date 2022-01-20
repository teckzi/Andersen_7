package com.teckzi.rickandmorty.presentation.screens.episode_detail_screen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teckzi.rickandmorty.domain.model.CharacterModel
import com.teckzi.rickandmorty.domain.model.EpisodeModel
import com.teckzi.rickandmorty.domain.use_cases.UseCases
import com.teckzi.rickandmorty.util.Constants
import com.teckzi.rickandmorty.util.Constants.EPISODE_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedEpisode: MutableStateFlow<EpisodeModel?> = MutableStateFlow(null)
    val selectedEpisode: StateFlow<EpisodeModel?> = _selectedEpisode

    init {
        val episodeId = savedStateHandle.get<Int>(EPISODE_ARGUMENT_KEY)
        viewModelScope.launch(Dispatchers.IO) {
            _selectedEpisode.value =
                episodeId?.let { useCases.getSelectedEpisodeUseCase(episodeId = episodeId) }
        }
        Log.d("TAG episode viewModel savedStateHandle", "$episodeId")
    }
}