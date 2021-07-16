package com.github.salihbasic.lavinija.data.dictionary

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

object DictionarySerializer : JsonContentPolymorphicSerializer<DictionaryEntry>(DictionaryEntry::class) {

    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out DictionaryEntry> =
        when(element.jsonObject["pos"]?.jsonPrimitive?.content) {
            "N" -> DictionaryNoun.serializer()
            "ADJ" -> DictionaryAdjective.serializer()
            "ADV" -> DictionaryAdverb.serializer()
            "CONJ" -> DictionaryConjunction.serializer()
            "INTERJ" -> DictionaryInterjection.serializer()
            "NUM" -> DictionaryNumber.serializer()
            "PACK" -> DictionaryPackon.serializer()
            "PREP" -> DictionaryPreposition.serializer()
            "PRON" -> DictionaryPronoun.serializer()
            "V" -> DictionaryVerb.serializer()
            else -> throw IllegalArgumentException("Nonsense argument at element: $element!")
    }

}