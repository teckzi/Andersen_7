package com.teckzi.domain.use_cases.location_use_cases

import com.teckzi.domain.model.LocationModel
import com.teckzi.domain.repository.IRepository

class GetLocationByName(
    private val repository: IRepository
) {
    suspend operator fun invoke(locationName: String): LocationModel? {
        return repository.getSelectedLocationByName(locationName)
    }
}