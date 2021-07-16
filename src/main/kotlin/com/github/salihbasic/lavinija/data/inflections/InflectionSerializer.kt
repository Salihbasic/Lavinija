package com.github.salihbasic.lavinija.data.inflections

import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

object InflectionSerializer
    : JsonContentPolymorphicSerializer<InflectionEntry>(InflectionEntry::class) {

    override fun selectDeserializer(element: JsonElement) = when(element.jsonObject["pos"]?.jsonPrimitive?.content) {
        "N" -> InflectionNoun.serializer()
        "PRON" -> InflectionPronoun.serializer()
        "ADJ" -> InflectionAdjective.serializer()
        "ADV" -> InflectionAdverb.serializer()
        "CONJ" -> InflectionConjunction.serializer()
        "INTERJ" -> InflectionInterjection.serializer()
        "NUM" -> InflectionNumber.serializer()
        "PREP" -> InflectionPreposition.serializer()
        "SUPINE" -> InflectionSupine.serializer()
        "V" -> InflectionVerb.serializer()
        "VPAR" -> InflectionVerbParticiple.serializer()
        else -> throw IllegalArgumentException("Nonsense arguments at element: $element!")
    }

}