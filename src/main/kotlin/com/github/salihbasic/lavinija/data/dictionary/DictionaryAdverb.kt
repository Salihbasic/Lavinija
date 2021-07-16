package com.github.salihbasic.lavinija.data.dictionary

import com.github.salihbasic.lavinija.data.inflections.InflectionAdverb
import com.github.salihbasic.lavinija.data.inflections.InflectionEntry
import kotlinx.serialization.Serializable

@Serializable
data class DictionaryAdverb(@Serializable(with = StemListSerializer::class) override val stems: List<String>,
                            override val pos: String,
                            val comparison: String,
                            override val age: String,
                            override val geography: String,
                            override val area: String,
                            override val frequency: String,
                            override val source: String,
                            override val senses: String) : DictionaryEntry() {

    override fun inflectionMatches(inflectionEntry: InflectionEntry): Boolean {

        // First ensure we are actually dealing with an ADVerb inflection
        if (inflectionEntry.pos != "ADV") return false
        val adverbInfl = inflectionEntry as InflectionAdverb

        val comparisonMatches = (this.comparison == adverbInfl.comparison)

        // Using for the index
        val inflectionStem = adverbInfl.stem - 1

        // Although this should never happen...
        if (inflectionStem < 0) return false

        return comparisonMatches

    }

}
