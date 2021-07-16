package com.github.salihbasic.lavinija.data.dictionary

import com.github.salihbasic.lavinija.data.inflections.InflectionAdjective
import com.github.salihbasic.lavinija.data.inflections.InflectionEntry
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DictionaryAdjective(@Serializable(with = StemListSerializer::class) override val stems: List<String>,
                               override val pos: String,
                               val declension: Int,
                               @SerialName("declension_variant") val declensionVariant: Int,
                               val comparison: String,
                               override val age: String,
                               override val geography: String,
                               override val area: String,
                               override val frequency: String,
                               override val source: String,
                               override val senses: String) : DictionaryEntry() {


    override fun inflectionMatches(inflectionEntry: InflectionEntry): Boolean {

        // First ensure we are actually dealing with an ADJective inflection
        if (inflectionEntry.pos != "ADJ") return false
        val adjectiveInfl = inflectionEntry as InflectionAdjective

        val declensionMatches = (this.declension == adjectiveInfl.declension)

        val anyVariant = (adjectiveInfl.declensionVariant == 0)
        val variantMatches = (anyVariant || (this.declensionVariant == adjectiveInfl.declensionVariant))

        val comparisonMatches = (adjectiveInfl.comparison == this.comparison)

        // Using for the index
        val inflectionStem = adjectiveInfl.stem - 1

        // Although this should never happen...
        if (inflectionStem < 0) return false

        return declensionMatches && variantMatches && comparisonMatches

    }

}
