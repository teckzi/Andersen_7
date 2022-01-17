package com.teckzi.rickandmorty.domain.use_cases.location_use_cases

import androidx.paging.PagingData
import com.teckzi.rickandmorty.domain.model.LocationModel
import com.teckzi.rickandmorty.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow

class GetSearchedLocationUseCase(
    private val repository: IRepository
) {
    suspend operator fun invoke(
        name: String?,
        type: String?,
        dimension: String?
    ): Flow<PagingData<LocationModel>> {
        return repository.searchLocation(
            name = name,
            type = type,
            dimension = dimension
        )
    }
}