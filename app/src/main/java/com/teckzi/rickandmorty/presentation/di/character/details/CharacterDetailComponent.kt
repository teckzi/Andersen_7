package com.teckzi.rickandmorty.presentation.di.character.details

import com.teckzi.rickandmorty.di.annotation.FragmentScope
import com.teckzi.rickandmorty.presentation.screens.character_detail_screen.CharacterDetailFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [CharacterDetailModule::class])
interface CharacterDetailComponent {
    fun inject(characterDetailFragment: CharacterDetailFragment)
}