package com.github.salihbasic.lavinija.data.dictionary

import com.github.salihbasic.lavinija.data.inflections.InflectionEntry
import com.github.salihbasic.lavinija.data.inflections.InflectionPronoun
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DictionaryPronoun(@Serializable(with = StemListSerializer::class) override val stems: List<String>,
                             override val pos: String,
                             val declension: Int,
                             @SerialName("declension_variant") val declensionVariant: Int,
                             @SerialName("pronoun_kind") val pronounKind: String,
                             override val age: String,
                             override val geography: String,
                             override val area: String,
                             override val frequency: String,
                             override val source: String,
                             override val senses: String) : DictionaryEntry() {

    override fun inflectionMatches(inflectionEntry: InflectionEntry): Boolean {

        // First ensure we are actually dealing with an PRONoun inflection
        if (inflectionEntry.pos != "PRON") return false
        val pronounInfl = inflectionEntry as InflectionPronoun

        val declensionMatches = (this.declension == pronounInfl.declension)

        val anyVariant = (pronounInfl.declensionVariant == 0)
        val variantMatches = (anyVariant || (this.declensionVariant == declensionVariant))

        // Using for the index
        val inflectionStem = pronounInfl.stem - 1

        // Although this should never happen...
        if (inflectionStem < 0) return false

        return declensionMatches && variantMatches

    }

}
