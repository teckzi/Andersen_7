package com.teckzi.rickandmorty.data.local

import androidx.room.TypeConverter
import com.teckzi.rickandmorty.util.getIdFromUrl

class DatabaseConverter {
    private val separator = ","

    @TypeConverter
    fun convertListToString(list: List<String>): String {
        return list.joinToString(separator)
    }

    @TypeConverter
    fun convertStringToList(string: String): List<String> {
        return string.split(separator)
    }

    @TypeConverter
    fun convertIntListToString(list: List<Int>): String {
        return list.joinToString(separator)
    }

    @TypeConverter
    fun convertStringToIntList(string: String): List<Int> {
        val list = mutableListOf<Int>()
        string.split(separator).let { listOfString ->
            listOfString.forEach {
                list.add(it.toInt())
            }
        }
        return list
    }
    @TypeConverter
    fun convertStringListToIntList(strings:List<String>): List<Int>{
        val list = mutableListOf<Int>()
        strings.forEach {
            list.add(it.getIdFromUrl()!!)
        }
        return list
    }
}