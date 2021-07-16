package com.github.salihbasic.lavinija.data.dictionary

import com.github.salihbasic.lavinija.data.inflections.InflectionConjunction
import com.github.salihbasic.lavinija.data.inflections.InflectionEntry
import kotlinx.serialization.Serializable

@Serializable
data class DictionaryConjunction(@Serializable(with = StemListSerializer::class) override val stems: List<String>,
                                 override val pos: String,
                                 override val age: String,
                                 override val geography: String,
                                 override val area: String,
                                 override val frequency: String,
                                 override val source: String,
                                 override val senses: String) : DictionaryEntry() {

    override fun inflectionMatches(inflectionEntry: InflectionEntry): Boolean {

        // First ensure we are actually dealing with an CONJunction inflection
        if (inflectionEntry.pos != "CONJ") return false
        val conjInfl = inflectionEntry as InflectionConjunction

        // Using for the index
        val inflectionStem = conjInfl.stem - 1

        // Although this should never happen...
        if (inflectionStem < 0) return false

        return true

    }

}
