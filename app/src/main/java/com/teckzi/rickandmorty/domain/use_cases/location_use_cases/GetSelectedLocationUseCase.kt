package com.teckzi.rickandmorty.domain.use_cases.location_use_cases

import com.teckzi.rickandmorty.domain.model.LocationModel
import com.teckzi.rickandmorty.domain.repository.IRepository

class GetSelectedLocationUseCase(
    private val repository: IRepository
) {
    suspend operator fun invoke(locationId: Int): LocationModel {
        return repository.getSelectedLocation(locationId)
    }
}