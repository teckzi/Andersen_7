package com.teckzi.rickandmorty.data.local


import androidx.test.core.app.ApplicationProvider
import com.teckzi.rickandmorty.data.local.dao.CharacterDao
import com.teckzi.rickandmorty.data.mappers.toCharacterDbo
import com.teckzi.rickandmorty.domain.model.CharacterModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class CharacterDaoTest {

    private lateinit var db: RickAndMortyDatabase
    private lateinit var characterDao: CharacterDao
    private val charList = listOf(
        CharacterModel(
            id = 1,
            name = "Eli's Girlfriend",
            status = "alive",
            species = "Human",
            gender = "female",
            type = "",
            origin = "Post-Apocalyptic Earth",
            location = "Post-Apocalyptic Earth",
            image = "https://rickandmortyapi.com/api/character/avatar/110.jpeg",
            episode = emptyList()
        ),
        CharacterModel(
            id = 2,
            name = "Eli's Girlfriend",
            status = "alive",
            species = "Human",
            gender = "female",
            type = "",
            origin = "Post-Apocalyptic Earth",
            location = "Post-Apocalyptic Earth",
            image = "https://rickandmortyapi.com/api/character/avatar/110.jpeg",
            episode = emptyList()
        ),
        CharacterModel(
            id = 3,
            name = "Eli's Girlfriend",
            status = "alive",
            species = "Human",
            gender = "female",
            type = "",
            origin = "Post-Apocalyptic Earth",
            location = "Post-Apocalyptic Earth",
            image = "https://rickandmortyapi.com/api/character/avatar/110.jpeg",
            episode = emptyList()
        )
    )

    @BeforeEach
    fun createDb() = runTest {
        db = RickAndMortyDatabase.create(
            context = ApplicationProvider.getApplicationContext(),
            useInMemory = true
        )
        characterDao = db.characterDao()

        val characters = charList.map { it.toCharacterDbo() }
        characterDao.addCharacters(characters)
    }

    @AfterEach
    fun cleanup() {
        db.clearAllTables()
    }

    @Test
    fun getCharacter() = runTest {

        val id = characterDao.getSelectedCharacter(1)

        assertNotNull(id)
        assertEquals(id.id!!, 1)
    }

    @Test
    fun insert() = runTest {

        val id = characterDao.getAllCharacters()

        assertNotNull(id)
        assertEquals(id.size, 3)
        assertEquals(id[0].name, charList[0].name)

        characterDao.addCharacters(charList.map { it.toCharacterDbo() })

        val id2 = characterDao.getAllCharacters()
        assertNotNull(id2)
        assertEquals(id2.size, 4)
    }

    @Test
    fun getAll() = runTest {

        val byId = characterDao.getAllCharacters()

        assertNotNull(byId)
        assertEquals(byId.size, 3)
        assertEquals(byId[0].name, charList[0].name)
    }
}