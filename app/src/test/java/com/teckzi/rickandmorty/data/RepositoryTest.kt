package com.teckzi.rickandmorty.data

import com.teckzi.rickandmorty.data.repository.Repository
import com.teckzi.rickandmorty.domain.model.CharacterModel
import com.teckzi.rickandmorty.domain.repository.LocalDataSource
import com.teckzi.rickandmorty.domain.repository.RemoteDataSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


@ExperimentalCoroutinesApi
class RepositoryTest {

    private lateinit var repository: Repository
    private val localDataSource = mockk<LocalDataSource>()
    private val remoteDataSource = mockk<RemoteDataSource>()

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        repository = Repository(
            local = localDataSource,
            remote = remoteDataSource
        )
    }

    @Test
    fun `getCharacterById should get character from local data source`() = runTest {
        val id = 1
        val character: CharacterModel = mockk()

        coEvery {
            localDataSource.getCharacterByIdLocal(id)
        } returns character

        repository.getCharacterById(id)

        coVerify { localDataSource.getCharacterByIdLocal(id) }
    }

    @Test
    fun `get character location`() = runTest {
        coEvery { localDataSource.getLocationByIdLocal(any()) } returns mockk()
        repository.getLocationById(1)

        coVerify {
            localDataSource.getLocationByIdLocal(1)
        }
    }

    @Test
    fun getCharacters() = runTest {
        val list = listOf(1, 2, 3)
        val character: CharacterModel = mockk()
        val listCharacters: List<CharacterModel> = listOf(character, character)

        coEvery {
            localDataSource.getCharactersListById(list)
        } returns listCharacters

        val result = repository.getCharacterListById(list)

        coVerify { localDataSource.getCharactersListById(list) }

        assertEquals(2, result.size)
        assertTrue(
            "Expected ${result[0]} to be an instance of ${CharacterModel::class}",
            CharacterModel::class.isInstance(result[0])
        )
    }

}