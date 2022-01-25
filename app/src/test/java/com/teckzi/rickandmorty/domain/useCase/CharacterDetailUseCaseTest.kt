package com.teckzi.rickandmorty.domain.useCase

import com.teckzi.rickandmorty.data.mappers.toCharacterModel
import com.teckzi.rickandmorty.data.network.model.CharacterDto
import com.teckzi.rickandmorty.data.network.model.Origin
import com.teckzi.rickandmorty.domain.repository.IRepository
import com.teckzi.rickandmorty.domain.use_cases.character_use_cases.GetSelectedCharacterUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class CharacterDetailUseCaseTest {

    @MockK
    lateinit var repository: IRepository

    lateinit var useCase: GetSelectedCharacterUseCase

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetSelectedCharacterUseCase(repository)
    }

    @Test
    fun `given successful response, when getCharacterById called, return character`() {
        runTest {
            val id = 30
            val response = CharacterDto(
                30,
                "",
                "",
                "",
                "",
                "",
                Origin("unknown", ""),
                Origin("unknown", ""),
                "",
                listOf(""),
                "",
                ""
            )

            coEvery { repository.getCharacterById(id) } answers { response.toCharacterModel() }

            useCase.invoke(id)

            coVerify { repository.getCharacterById(id) }
        }
    }
}