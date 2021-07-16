package com.github.salihbasic.lavinija.data.inflections

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InflectionVerb(override val pos: String,
                          val conjugation: Int,
                          @SerialName("conjugation_variant") val conjugationVariant: Int,
                          val tense: String,
                          val voice: String,
                          val mood: String,
                          val person: Int,
                          val number: String,
                          override val stem: Int,
                          override val characters: Int,
                          override val ending: String,
                          override val age: String,
                          override val frequency: String) : InflectionEntry()
