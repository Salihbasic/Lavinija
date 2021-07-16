package com.github.salihbasic.lavinija.data.dictionary

import com.github.salihbasic.lavinija.data.inflections.InflectionEntry
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DictionaryPackon(@Serializable(with = StemListSerializer::class) override val stems: List<String>,
                            override val pos: String,
                            val declension: Int,
                            @SerialName("declension_variant") val declensionVariant: Int,
                            @SerialName("packon_kind") val packonKind: String,
                            override val age: String,
                            override val geography: String,
                            override val area: String,
                            override val frequency: String,
                            override val source: String,
                            override val senses: String) : DictionaryEntry() {

    override fun inflectionMatches(inflectionEntry: InflectionEntry): Boolean {
        // TODO: Figure out how packons work
        return false
    }

}
