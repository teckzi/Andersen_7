package com.teckzi.rickandmorty.util

object Constants {
    const val BASE_URL = "https://rickandmortyapi.com/api/"

    const val RICK_DATABASE = "rick_and_morty_database"
    const val CHARACTER_DATABASE_TABLE = "character_table"
    const val LOCATION_DATABASE_TABLE = "location_table"
    const val EPISODE_DATABASE_TABLE = "episode_table"

    const val CHARACTER_REMOTE_KEY_TABLE = "character_remote_key_table"
    const val LOCATION_REMOTE_KEY_TABLE = "location_remote_key_table"
    const val EPISODE_REMOTE_KEY_TABLE = "episode_remote_key_table"

    const val ITEMS_PER_PAGE = 20

    const val CHARACTER_ARGUMENT_KEY = "characterId"
    const val LOCATION_ARGUMENT_KEY = "locationId"
    const val EPISODE_ARGUMENT_KEY = "episodeId"

    const val LOCATION_TYPE = "location"
    const val EPISODE_TYPE = "episode"
    const val CHARACTER_TYPE = "character"

    const val FILTER_TYPE_ARGUMENT_KEY = "filter_type_arg_key"
    const val FILTER_RETURN_BACK_TO_CHARACTER = "filter_back_to_character"
    const val FILTER_RETURN_BACK_TO_LOCATION = "filter_back_to_location"
    const val FILTER_RETURN_BACK_TO_EPISODE = "filter_back_to_episode"
}