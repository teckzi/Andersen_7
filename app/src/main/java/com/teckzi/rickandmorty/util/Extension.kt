package com.teckzi.rickandmorty.util

import android.view.View

// String
fun String.getPageIdFromUrl() = this.split("?page=")[1].substringBefore("&").toInt()

fun String.getIdFromUrl() = try {
    when {
        this.contains("/episode/") -> this.split("/episode/")[1].toInt()
        this.contains("/location/") -> this.split("/location/")[1].toInt()
        else -> this.split("/character/")[1].toInt()
    }
} catch (e: IndexOutOfBoundsException) {
    0
}


fun String.getEpisodeString() = "Season ${
    this.split("S")[1].substringBefore("E").toInt()
}, Episode ${this.split("E")[1].toInt()}"

fun String.setStringToSeason(): String {
    var result = ""
    if (this[0] == 'S') result = "S" + this.split(" ")[1].let {
        if (it.toInt() >= 10) it
        else "0$it"
    }
    else if (this[0] == 'E') result = "E" + this.split(" ")[1].let {
        if (it.toInt() >= 10) it
        else "0$it"
    }
    return result
}

fun List<String>.addToIntList(): List<Int> {
    val listOfInts = mutableListOf<Int>()
    this.forEach {
        listOfInts.add(it.getIdFromUrl())
    }
    return listOfInts
}

fun List<Int>.addToStringList(type: String): List<String> {
    val listOfString = mutableListOf<String>()
    this.forEach {
        listOfString.add("https://rickandmortyapi.com/api/$type/$it")
    }
    return listOfString
}

// View
fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visible() {
    visibility = View.VISIBLE
}