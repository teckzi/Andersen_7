package com.teckzi.rickandmorty.data.paging_source.character_paging

import android.net.Uri
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.teckzi.rickandmorty.data.local.RickAndMortyDatabase
import com.teckzi.rickandmorty.data.mappers.toCharacterDbo
import com.teckzi.rickandmorty.data.mappers.toCharacterModel
import com.teckzi.rickandmorty.data.network.RickAndMortyApi
import com.teckzi.rickandmorty.domain.model.CharacterModel
import javax.inject.Inject

private const val TAG = "TAG SearchCharacterSource"

class SearchCharacterSource @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi,
    private val rickAndMortyDatabase: RickAndMortyDatabase,
    private val name: String?,
    private val status: String?,
    private val species: String?,
    private val type: String?,
    private val gender: String?
) : PagingSource<Int, CharacterModel>() {

    private val characterDao = rickAndMortyDatabase.characterDao()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
        var results: List<CharacterModel>

        val page: Int = params.key ?: 1
        val pageSize = params.loadSize
        var nextKey: Int? = null

        try {
            rickAndMortyApi.getCharacters(page, name, status, species, type, gender).apply {

                if (this.info.next != null) {
                    val uri = Uri.parse(this.info.next)
                    val nextPageQuery = uri.getQueryParameter("page")
                    nextKey = nextPageQuery?.toInt()
                }

                results = this.results.map { it.toCharacterModel() }

                results.let { characterList ->
                    characterDao.addCharacters(characterList.map { it.toCharacterDbo() })
                }

            }
        } catch (e: Exception) {
            Log.d(TAG, "exception $e   cache ${characterDao.getAllCharacters()}")
            characterDao.getFilteredCharacters(name, status, species, type, gender).apply {
                nextKey = if (size < pageSize) null else nextKey?.plus(1)
                results = this.map { it.toCharacterModel() }
            }
        }

        return LoadResult.Page(data = results, prevKey = null, nextKey = nextKey)
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }
}
