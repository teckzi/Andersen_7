package com.teckzi.rickandmorty.data.local.model

import androidx.room.Embedded
import androidx.room.Relation
import com.teckzi.rickandmorty.domain.model.CharacterModel
import com.teckzi.rickandmorty.domain.model.EpisodeModel
import com.teckzi.rickandmorty.domain.model.LocationModel

//data class CharacterFinal(
//    @Embedded val characterModel: CharacterModel,
//    @Relation(parentColumn = "origin", entityColumn = "id")
//    val origin: LocationModel,
//    @Relation(parentColumn = "location", entityColumn = "id")
//    val locationName: LocationModel,
//    @Relation(parentColumn = "episode", entityColumn = "id")
//    val episodeList:List<EpisodeModel>
//)