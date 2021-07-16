package com.github.salihbasic.lavinija

import com.github.salihbasic.lavinija.data.dictionary.Dictionary
import com.github.salihbasic.lavinija.data.inflections.Inflections
import com.github.salihbasic.lavinija.search.SearchOptions
import com.github.salihbasic.lavinija.search.Searcher
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {

    /*
    Temporary testing interface.

    More detailed implementation later.
     */

    val dictionary = Dictionary()
    val inflections = Inflections()

    val searchOptions = setOf(
        SearchOptions.REPLACE_I_WITH_J,
        SearchOptions.REPLACE_V_WITH_U
    )

    val searcher = Searcher(dictionary, inflections, searchOptions)

    while (true) {

        println("Waiting for input...")

        val input = readLine()?.split(" ") ?: setOf()

        if (input.contains("END")) break

        println("""
            Received input: $input. Analysing...
        """.trimIndent())

        searcher.setWords(input.toSet())

        if (searcher.matchWords().isEmpty()) {

            println("""
                No matches could be found.
            """.trimIndent())

            continue
        }

        println("""
            It took ${measureTimeMillis { searcher.matchWords() }} ms to find all possible matches.
            
            All the matches are as follows:
            
        """.trimIndent())

        for (match in searcher.matchWords()) {

            println("""
                Stem: ${match.first.stems[0]}
                Ending: ${match.second.ending}
                
                Match: $match
                
            """.trimIndent())

        }

    }

}