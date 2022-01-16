package com.teckzi.rickandmorty.presentation.screens.character_screen

import androidx.lifecycle.ViewModel
import com.teckzi.rickandmorty.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {


}