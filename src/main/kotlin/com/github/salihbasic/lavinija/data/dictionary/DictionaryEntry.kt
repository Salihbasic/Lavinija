package com.github.salihbasic.lavinija.data.dictionary

import com.github.salihbasic.lavinija.data.inflections.InflectionEntry
import kotlinx.serialization.Serializable

/**
 *
 */
@Serializable
sealed class DictionaryEntry {

    abstract val stems: List<String>
    abstract val pos: String
    abstract val age: String
    abstract val geography: String
    abstract val area: String
    abstract val frequency: String
    abstract val source: String
    abstract val senses: String

    abstract fun inflectionMatches(inflectionEntry: InflectionEntry): Boolean

}