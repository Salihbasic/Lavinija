package com.github.salihbasic.lavinija.data.dictionary

import com.github.salihbasic.lavinija.data.inflections.InflectionEntry
import com.github.salihbasic.lavinija.data.inflections.InflectionVerb
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DictionaryVerb(@Serializable(with = StemListSerializer::class) override val stems: List<String>,
                          override val pos: String,
                          val conjugation: Int,
                          @SerialName("conjugation_variant") val conjugationVariant: Int,
                          @SerialName("verb_kind") val verbKind: String,
                          override val age: String,
                          override val geography: String,
                          override val area: String,
                          override val frequency: String,
                          override val source: String,
                          override val senses: String) : DictionaryEntry() {

    override fun inflectionMatches(inflectionEntry: InflectionEntry): Boolean {

        // First ensure we are actually dealing with an Verb inflection
        if (inflectionEntry.pos != "V") return false
        val verbInfl = inflectionEntry as InflectionVerb

        val conjugationMatches = (this.conjugation == verbInfl.conjugation)

        val anyVariant = (verbInfl.conjugationVariant == 0)
        val variantMatches = (if (anyVariant) true else (this.conjugationVariant == verbInfl.conjugationVariant))

        // Using for the index
        val inflectionStem = verbInfl.stem - 1

        // Although this should never happen...
        if (inflectionStem < 0) return false

        return conjugationMatches && variantMatches

    }

}
