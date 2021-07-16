package com.github.salihbasic.lavinija.data.inflections

import com.github.salihbasic.lavinija.data.DataLoader
import com.github.salihbasic.lavinija.data.INFLECTS_PATH

/**
 * Represents a top-level abstraction for all inflection related operations
 *
 * It directly loads inflections based on a provided filepath and then proceeds to
 * sort inflections based on the part of speech they represent, storing them in map with the key being their
 * specific part of speech.
 *
 * Default inflections filepath is used if none other is provided.
 */
class Inflections(inflectionsPath: String = INFLECTS_PATH) {

    private val inflections: List<InflectionEntry> = DataLoader.loadInflections(inflectionsPath)

    private val sortedInflections = mapOf(
        "N" to this.sortEntries("N"),
        "ADJ" to this.sortEntries("ADJ"),
        "CONJ" to this.sortEntries("CONJ"),
        "INTERJ" to this.sortEntries("INTERJ"),
        "NUM" to this.sortEntries("NUM"),
        "PREP" to this.sortEntries("PREP"),
        "PRON" to this.sortEntries("PRON"),
        "SUPINE" to this.sortEntries("SUPINE"),
        "V" to this.sortEntries("V"),
        "VPAR" to this.sortEntries("VPAR")
    )

    /**
     * Wraps around the binary search to return all inflection entries matching the particular ending provided.
     *
     * @param ending Ending to be searched
     * @param pos Part of speech for the ending, *"X"* (by default) for any ending
     *
     * @return List of [InflectionEntry] matching the ending criteria
     */
    fun findEntries(ending: String, pos: String = "X"): List<InflectionEntry> {
        val result = mutableListOf<InflectionEntry>()

        for (index in binarySearch(ending, pos)) {

            result.add(this.inflections[index])

        }

        return result
    }

    /**
     * Performs binary search to find all indices of a particular ending
     *
     * If value of part of speech is specified, only the endings for the given part of
     * speech will be searched, otherwise all endings shall be searched. This can be useful
     * for more specific filtering or just reducing search time.
     *
     * If the part of speech is set to *"X"*, then all endings shall be searched.
     *
     * @param ending Ending to be searched
     * @param pos Part of speech the ending should pertain to
     *
     */
    fun binarySearch(ending: String, pos: String = "X"): List<Int> {
        val result = mutableListOf<Int>()

        if (pos == "X") { // Go through all inflections, default

            for (key in this.sortedInflections.keys) {
                result.addAll(this.binarySearchInflectsRange(ending, key))
            }

        } else { // Only find specific entries

            result.addAll(this.binarySearchInflectsRange(ending, pos))

        }

        return result.distinct()
    }

    /**
     * Finds all indices of a particular ending based on the provided part of speech.
     *
     * Since binary search can only find one index, it alone is not enough, because oftentimes there are multiple
     * endings. These endings are necessarily next to each other (since they are sorted), so after finding the index,
     * we do a linear search (which shouldn't have any major impact on performance) to pick up the rest of indices.
     */
    fun binarySearchInflectsRange(ending: String, pos: String): List<Int> {
        val foundIndex = this.binarySearchInflections(ending, pos)
        if (foundIndex < 0) return emptyList()

        val inflects = this.sortedInflections[pos] ?: emptyList()
        val result = mutableListOf(inflects[foundIndex].second)

        // Since both sorting and binary search are stable, we only need to scan the left side
        var leftIndex = (foundIndex - 1).coerceAtLeast(0)

        while (leftIndex >= 0 && inflects[leftIndex].first == ending) {
            result.add(inflects[leftIndex].second)
            leftIndex--
        }

        return result
    }

    /*
        Code for a more efficient binary search adapted from:
        - https://github.com/scandum/binary_search

        (See monobound_quaternary_search() in binary_search.c)
     */
    private fun binarySearchInflections(ending: String, pos: String): Int {

        if (ending.isEmpty()) return -1

        val inflects = this.sortedInflections[pos] ?: return -1

        var low = 0
        var high = inflects.size
        var mid: Int

        while (high >= 65536) {

            mid = high / 4
            high -= mid * 3

            if (ending < inflects[low + mid * 2].first) {

                if (ending >= inflects[low + mid].first) {

                    low += mid

                }

            } else {

                low += mid * 2

                if (ending >= inflects[low + mid].first) {

                    low += mid

                }

            }

        }

        while (high > 3) {

            mid = high / 2

            if (ending >= inflects[low + mid].first) {

                low += mid

            }

            high -= mid

        }

        while (high-- > 0) {

            if (ending == inflects[low + high].first) {

                return low + high

            }

        }

        return -1

    }

    private fun sortEntries(pos: String): List<Pair<String, Int>> {
        if (pos.isEmpty()) throw IllegalArgumentException("You must specify a part of speech for sorting!")

        val result = mutableListOf<Pair<String, Int>>()

        for (indexedEntry in this.inflections.withIndex()) {

            val ending = if (indexedEntry.value.pos == pos) indexedEntry.value.ending else continue

            result.add(Pair(ending, indexedEntry.index))

        }

        return result.sortedBy { pair -> pair.first }

    }

}