package com.github.salihbasic.lavinija.data.dictionary

import com.github.salihbasic.lavinija.data.inflections.InflectionEntry
import com.github.salihbasic.lavinija.data.inflections.InflectionNumber
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DictionaryNumber(@Serializable(with = StemListSerializer::class) override val stems: List<String>,
                            override val pos: String,
                            val declension: Int,
                            @SerialName("declension_variant") val declensionVariant: Int,
                            @SerialName("numeral_sort") val numeralSort: String,
                            @SerialName("numeral_value") val numeralValue: Int,
                            override val age: String,
                            override val geography: String,
                            override val area: String,
                            override val frequency: String,
                            override val source: String,
                            override val senses: String) : DictionaryEntry() {

    override fun inflectionMatches(inflectionEntry: InflectionEntry): Boolean {

        // First ensure we are actually dealing with an NUMber inflection
        if (inflectionEntry.pos != "NUM") return false
        val numberInfl = inflectionEntry as InflectionNumber

        val declensionMatches =  (this.declension == numberInfl.declension)

        val anyVariant = (numberInfl.declensionVariant == 0)
        val variantMatches = (anyVariant || (this.declensionVariant == numberInfl.declensionVariant))

        val numeralSortMatches = (this.numeralSort == numberInfl.numeralSort)

        // Using for the index
        val inflectionStem = numberInfl.stem - 1

        // Although this should never happen...
        if (inflectionStem < 0) return false

        return declensionMatches && variantMatches && numeralSortMatches

    }

}
