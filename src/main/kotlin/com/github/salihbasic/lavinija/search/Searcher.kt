package com.github.salihbasic.lavinija.search

import com.github.salihbasic.lavinija.data.dictionary.Dictionary
import com.github.salihbasic.lavinija.data.dictionary.DictionaryEntry
import com.github.salihbasic.lavinija.data.inflections.InflectionEntry
import com.github.salihbasic.lavinija.data.inflections.Inflections
import java.io.File

class Searcher(val dictionary: Dictionary,
               val inflections: Inflections,
               private val searchOptions: Set<SearchOptions>,
               private var words: Set<String> = setOf()) {

    constructor(dictionary: Dictionary, inflections: Inflections, searchOptions: Set<SearchOptions>, wordFile: File) :
            this(dictionary,
                inflections,
                searchOptions,
                wordFile.readText().split(" ").toSet())

    constructor(dictionary: Dictionary, inflections: Inflections, searchOptions: Set<SearchOptions>, text: String) :
            this(dictionary,
                inflections,
                searchOptions,
                text.split(" ").toSet())

    fun matchWords(): List<Pair<DictionaryEntry, InflectionEntry>> {
        val result = mutableListOf<Pair<DictionaryEntry, InflectionEntry>>()

        for (word in this.words) {

            val pairs = this.pairStemsWithEndings(word)

            for ((stem, ending) in pairs) {

                var dictionaryEntries = this.dictionary.findEntries(stem)


                if (dictionaryEntries.isEmpty()) {

                    /*
                        Dictionary is case-sensitive.

                        Consider the words "amor" and "Amor". The former would find a match, while the latter
                        would not. The reason being that the sorting algorithm (and consequently binary search)
                        would place these two in entirely different locations and they would be considered
                        completely distinct.

                        To resolve this problem, we can simply turn the entire word to lowercase, but this is not
                        always possible, because sometimes words need to start with an uppercase letter (names, for instance).

                        So the best possible solution would be to simply check if the original word (provided as-is) returns no
                        results, and if it does not, only then turn it to lower case.
                    */
                    if (WordUtility.containsUppercase(stem)) {
                        dictionaryEntries = this.dictionary.findEntries(stem.lowercase())
                    }

                }

                for (definition in dictionaryEntries) {

                    val possibleEndings = this.inflections.findEntries(ending, definition.pos)

                    for (matchingEnding in possibleEndings) {

                        if (definition.inflectionMatches(matchingEnding)) {

                            result.add(Pair(definition, matchingEnding))

                        }

                    }

                }

            }

        }

        return result

    }

    /**
     * The basic algorithm goes through the entire word, each time removing one letter from
     * the stem and adding it to the ending. This goes all the way until the stem is completely empty,
     * at which point there is nothing more to be searched, since a stem must exist.
     *
     * @param word Word to be run through
     * @param emptyEnding Whether endings which are empty should be included, *true* by default
     *
     * @return List of pairs of word's possible stems and endings
     */
    fun pairStemsWithEndings(word: String, emptyEnding: Boolean = true): List<Pair<String, String>> {
        val result = mutableListOf<Pair<String, String>>()

        var strippedWord = WordUtility.stripPunctuation(word)

        if (this.searchOptions.contains(SearchOptions.CASE_INSENSITIVE)) {
            strippedWord = strippedWord.lowercase()
        }

        if (this.searchOptions.contains(SearchOptions.REPLACE_V_WITH_U)) {
            strippedWord = WordUtility.replaceVwithU(strippedWord)
        }

        if (this.searchOptions.contains(SearchOptions.REPLACE_I_WITH_J)) {
            strippedWord = WordUtility.replaceIwithJ(strippedWord)
        }

        for (index in 0..strippedWord.length) {

            val stem = strippedWord.substring(0, index)
            val ending = strippedWord.substring(index)

            /*
                Stem can never be empty.

                If empty ending parameter is passed as false, then empty endings should be ignored too.
             */
            if (stem.isEmpty() || (ending.isEmpty() && !emptyEnding)) continue

            result.add(Pair(stem, ending))

        }

        return result.reversed()

    }

    fun setWord(word: String) {
        this.words = setOf(word)
    }

    fun setWords(words: Set<String>) {
        this.words = words
    }

}