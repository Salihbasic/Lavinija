package com.github.salihbasic.lavinija.data.dictionary

import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonTransformingSerializer

object StemListSerializer : JsonTransformingSerializer<List<String>>(ListSerializer(String.serializer()))