package com.github.salihbasic.lavinija.data.dictionary

import com.github.salihbasic.lavinija.data.inflections.InflectionEntry
import com.github.salihbasic.lavinija.data.inflections.InflectionNoun
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DictionaryNoun(@Serializable(with = StemListSerializer::class) override val stems: List<String>,
                          override val pos: String,
                          val declension: Int,
                          @SerialName("declension_variant") val declensionVariant: Int,
                          val gender: String,
                          @SerialName("noun_kind") val nounKind: String,
                          override val age: String,
                          override val geography: String,
                          override val area: String,
                          override val frequency: String,
                          override val source: String,
                          override val senses: String) : DictionaryEntry() {

    override fun inflectionMatches(inflectionEntry: InflectionEntry): Boolean {

        // First ensure we are actually dealing with an Noun inflection
        if (inflectionEntry.pos != "N") return false
        val nounInfl = inflectionEntry as InflectionNoun

        val declensionMatches = (this.declension == nounInfl.declension)

        val anyVariant = (nounInfl.declensionVariant == 0)
        val variantMatches = (anyVariant || (this.declensionVariant == nounInfl.declensionVariant))

        val anyGender = (nounInfl.gender == "C") || (nounInfl.gender == "X")
        val genderMatches = (if (anyGender) true else (this.gender == nounInfl.gender))

        // Using for the index
        val inflectionStem = nounInfl.stem - 1

        // Although this should never happen...
        if (inflectionStem < 0) return false

        return declensionMatches && variantMatches && genderMatches

    }

}
