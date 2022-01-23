package com.teckzi.rickandmorty.presentation.di.location.details

import com.teckzi.rickandmorty.di.annotation.FragmentScope
import com.teckzi.rickandmorty.presentation.screens.location_detail_screen.LocationDetailFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [LocationDetailModule::class])
interface LocationDetailComponent {
    fun inject(locationDetailFragment: LocationDetailFragment)
}