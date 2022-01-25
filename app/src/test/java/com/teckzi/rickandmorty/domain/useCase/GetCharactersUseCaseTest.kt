package com.teckzi.rickandmorty.domain.useCase

import com.teckzi.rickandmorty.data.repository.Repository
import com.teckzi.rickandmorty.domain.model.CharacterModel
import com.teckzi.rickandmorty.domain.use_cases.character_use_cases.GetCharacterListById
import com.teckzi.rickandmorty.testCharacters
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class GetCharactersUseCaseTest {

    private val repository = mockk<Repository>()
    private val useCase = GetCharacterListById(repository)

    @Test
    fun `given character list, when invoke, then return same list`() =
        runTest {
            val fakeCharacters = testCharacters
            stubRepository(fakeCharacters)

            val characters = useCase.invoke(listOf(1, 2, 3))

            assert(characters == fakeCharacters)
        }

    private fun stubRepository(characters: List<CharacterModel>) {
        coEvery { repository.getCharacterListById(listOf(1, 2, 3)) } coAnswers {
            characters
        }
    }
}