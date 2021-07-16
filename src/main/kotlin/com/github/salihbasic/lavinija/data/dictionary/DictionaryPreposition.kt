package com.github.salihbasic.lavinija.data.dictionary

import com.github.salihbasic.lavinija.data.inflections.InflectionEntry
import com.github.salihbasic.lavinija.data.inflections.InflectionPreposition
import kotlinx.serialization.Serializable

@Serializable
data class DictionaryPreposition(@Serializable(with = StemListSerializer::class) override val stems: List<String>,
                                 override val pos: String,
                                 val case: String,
                                 override val age: String,
                                 override val geography: String,
                                 override val area: String,
                                 override val frequency: String,
                                 override val source: String,
                                 override val senses: String) : DictionaryEntry() {

    override fun inflectionMatches(inflectionEntry: InflectionEntry): Boolean {

        // First ensure we are actually dealing with an PREPosition inflection
        if (inflectionEntry.pos != "PREP") return false
        val prepInfl = inflectionEntry as InflectionPreposition

        val caseMatches = (this.case == prepInfl.case)

        // Using for the index
        val inflectionStem = prepInfl.stem - 1

        // Although this should never happen...
        if (inflectionStem < 0) return false

        return caseMatches

    }

}
