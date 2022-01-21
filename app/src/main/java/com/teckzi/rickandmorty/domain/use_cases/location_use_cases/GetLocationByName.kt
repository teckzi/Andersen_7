package com.teckzi.rickandmorty.domain.use_cases.location_use_cases

import com.teckzi.rickandmorty.domain.model.LocationModel
import com.teckzi.rickandmorty.domain.repository.IRepository

class GetLocationByName(
    private val repository: IRepository
) {
    suspend operator fun invoke(locationName: String): LocationModel? {
        return repository.getSelectedLocationByName(locationName)
    }
}