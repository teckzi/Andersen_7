package com.teckzi.rickandmorty.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.teckzi.ricks.data.local.model.EpisodeDbo

@Dao
interface EpisodeDao {

    @Query("SELECT * FROM episode_table ORDER BY id ASC")
    suspend fun getAllEpisodes(): List<EpisodeDbo>

    @Query("SELECT * FROM episode_table WHERE id=:episodeId")
    suspend fun getSelectedEpisode(episodeId: Int): EpisodeDbo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEpisodes(episodes: List<EpisodeDbo>)

    @Query("SELECT * FROM episode_table WHERE name LIKE ifnull(:name, '%%') AND episode LIKE ifnull(:episode, '%%') ORDER BY id ASC")
    suspend fun getFilteredEpisodes(
        name: String?,
        episode: String?
    ): List<EpisodeDbo>
}