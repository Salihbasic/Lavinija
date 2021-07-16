package com.github.salihbasic.lavinija.data.inflections

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InflectionAdjective(override val pos: String,
                               val declension: Int,
                               @SerialName("declension_variant") val declensionVariant: Int,
                               val case: String,
                               val number: String,
                               val gender: String,
                               val comparison: String,
                               override val stem: Int,
                               override val characters: Int,
                               override val ending: String,
                               override val age: String,
                               override val frequency: String) : InflectionEntry()
