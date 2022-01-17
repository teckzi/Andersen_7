package com.teckzi.rickandmorty.domain.use_cases.location_use_cases

import androidx.paging.PagingData
import com.teckzi.rickandmorty.domain.model.LocationModel
import com.teckzi.rickandmorty.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow

class GetAllLocationUseCase(
    private val repository: IRepository
) {
    operator fun invoke(): Flow<PagingData<LocationModel>> {
        return repository.getAllLocation()
    }
}