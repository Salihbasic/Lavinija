package com.github.salihbasic.lavinija.data.inflections

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InflectionVerbParticiple(override val pos: String,
                                    val conjugation: Int,
                                    @SerialName("conjugation_variant") val conjugationVariant: Int,
                                    val case: String,
                                    val number: String,
                                    val gender: String,
                                    val tense: String,
                                    val voice: String,
                                    val mood: String,
                                    override val stem: Int,
                                    override val characters: Int,
                                    override val ending: String,
                                    override val age: String,
                                    override val frequency: String) : InflectionEntry()
