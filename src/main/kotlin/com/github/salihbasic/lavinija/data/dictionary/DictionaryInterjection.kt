package com.github.salihbasic.lavinija.data.dictionary

import com.github.salihbasic.lavinija.data.inflections.InflectionEntry
import com.github.salihbasic.lavinija.data.inflections.InflectionInterjection
import kotlinx.serialization.Serializable

@Serializable
data class DictionaryInterjection(@Serializable(with = StemListSerializer::class) override val stems: List<String>,
                                  override val pos: String,
                                  override val age: String,
                                  override val geography: String,
                                  override val area: String,
                                  override val frequency: String,
                                  override val source: String,
                                  override val senses: String) : DictionaryEntry() {

    override fun inflectionMatches(inflectionEntry: InflectionEntry): Boolean {

        // First ensure we are actually dealing with an INTERJ inflection
        if (inflectionEntry.pos != "INTERJ") return false
        val interjInfl = inflectionEntry as InflectionInterjection

        // Using for the index
        val inflectionStem = interjInfl.stem - 1

        // Although this should never happen...
        if (inflectionStem < 0) return false

        return true

    }

}
