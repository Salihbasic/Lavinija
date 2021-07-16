package com.github.salihbasic.lavinija.data

import com.github.salihbasic.lavinija.data.dictionary.DictionaryEntry
import com.github.salihbasic.lavinija.data.dictionary.DictionarySerializer
import com.github.salihbasic.lavinija.data.inflections.InflectionEntry
import com.github.salihbasic.lavinija.data.inflections.InflectionSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

const val DICTLINE_PATH = "src/main/resources/DICTLINE.json"
const val INFLECTS_PATH = "src/main/resources/INFLECTS.json"

object DataLoader {

    /**
     * Loads the dictionary directly in memory based on the provided filepath
     */
    fun loadDictionary(dataSource: String = DICTLINE_PATH): List<DictionaryEntry> {
        val dictionaryFile = File(dataSource)

        if (!dictionaryFile.exists()) throw FileNotFoundException()
        if (!dictionaryFile.canRead()) throw IOException("Could not read the provided dictionary file!")

        return Json.decodeFromString(ListSerializer(DictionarySerializer), dictionaryFile.readText())
    }

    /**
     * Loads the inflections directly in memory based on the provided filepath
     */
    fun loadInflections(dataSource: String = INFLECTS_PATH): List<InflectionEntry> {
        val inflectionsFile = File(dataSource)

        if (!inflectionsFile.exists()) throw FileNotFoundException()
        if (!inflectionsFile.canRead()) throw IOException("Could not read the provided inflections file!")

        return Json.decodeFromString(ListSerializer(InflectionSerializer), inflectionsFile.readText())
    }

}