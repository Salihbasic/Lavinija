package com.github.salihbasic.lavinija.data.inflections

import kotlinx.serialization.Serializable

@Serializable
data class InflectionPreposition(override val pos: String,
                                 val case: String,
                                 override val stem: Int,
                                 override val characters: Int,
                                 override val ending: String,
                                 override val age: String,
                                 override val frequency: String) : InflectionEntry()
