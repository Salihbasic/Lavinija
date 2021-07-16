package com.github.salihbasic.lavinija.data.inflections

import kotlinx.serialization.Serializable

const val NO_ENDING = "NO_ENDING"

@Serializable
sealed class InflectionEntry {

    abstract val pos: String
    abstract val stem: Int
    abstract val characters: Int
    abstract val ending: String
    abstract val age: String
    abstract val frequency: String

    fun inflectionMatches(inflection: String): Boolean {
        return ending.contentEquals(inflection) || (inflection.isEmpty() && ending.contentEquals(NO_ENDING))
    }

}
