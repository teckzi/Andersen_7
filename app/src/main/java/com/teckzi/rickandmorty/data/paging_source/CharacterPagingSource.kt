package com.teckzi.rickandmorty.data.paging_source

import android.net.Uri
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.teckzi.rickandmorty.data.local.RickAndMortyDatabase
import com.teckzi.rickandmorty.data.mappers.toCharacterDbo
import com.teckzi.rickandmorty.data.mappers.toCharacterModel
import com.teckzi.rickandmorty.data.network.RickAndMortyApi
import com.teckzi.rickandmorty.domain.model.CharacterModel

class CharacterPagingSource(
    private val rickAndMortyDatabase: RickAndMortyDatabase,
    private val rickAndMortyApi: RickAndMortyApi
) : PagingSource<Int, CharacterModel>() {

    private val characterDao = rickAndMortyDatabase.characterDao()

    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
        var results: List<CharacterModel>

        val page: Int = params.key ?: 1
        val pageSize = params.loadSize
        var nextKey: Int? = null

        try {
            rickAndMortyApi.getCharacters(page).apply {

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
            Log.d("TAG 1", "exception $e   cache ${characterDao.getAllCharacters()}")
            characterDao.getAllCharacters().apply {
                nextKey = if (size < pageSize) null else nextKey?.plus(1)
                results = this.map { it.toCharacterModel() }
            }
        }

        return LoadResult.Page(data = results, prevKey = null, nextKey = nextKey)
    }
}