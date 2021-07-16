package com.github.salihbasic.lavinija.data.inflections

import kotlinx.serialization.Serializable

@Serializable
data class InflectionAdverb(override val pos: String,
                            val comparison: String,
                            override val stem: Int,
                            override val characters: Int,
                            override val ending: String,
                            override val age: String,
                            override val frequency: String) : InflectionEntry()
