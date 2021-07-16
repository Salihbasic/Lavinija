package com.github.salihbasic.lavinija.data.inflections

import kotlinx.serialization.Serializable

@Serializable
data class InflectionInterjection(override val pos: String,
                                  override val stem: Int,
                                  override val characters: Int,
                                  override val ending: String,
                                  override val age: String,
                                  override val frequency: String) : InflectionEntry()
