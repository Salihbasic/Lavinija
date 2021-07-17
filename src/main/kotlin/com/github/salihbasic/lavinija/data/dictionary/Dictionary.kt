package com.github.salihbasic.lavinija.data.dictionary

import com.github.salihbasic.lavinija.data.DICTLINE_PATH
import com.github.salihbasic.lavinija.data.DataLoader
import com.github.salihbasic.lavinija.search.WordUtility

/**
 * Represents a top-level abstraction for all dictionary related operations
 *
 * It directly loads a dictionary based on a provided filepath and then proceeds to
 * sort entries based on their index in the JSON lists, thus allowing easy and efficient binary search.
 *
 * Default dictionary filepath is used if none other is provided.
 */
class Dictionary(dictionaryPath: String = DICTLINE_PATH) {

    private val entries: List<DictionaryEntry> = DataLoader.loadDictionary(dictionaryPath)
    private val sortedStems = listOf(
        this.sortEntries(0),
        this.sortEntries(1),
        this.sortEntries(2),
        this.sortEntries(3)
    )

    /**
     * Wraps around the binary search to return all dictionary entries matching the particular stem provided.
     *
     * @param stem Stem to be searched
     * @return All [DictionaryEntry] objects matching the particular stem
     */
    fun findEntries(stem: String): List<DictionaryEntry> {
        val result = mutableListOf<DictionaryEntry>()

        for (index in binarySearch(stem)) {

            result.add(this.entries[index])

        }

        return result
    }

    /**
     * Performs binary search on the entire dictionary and returns a list of indices for every stem
     * in the actual dictionary.
     *
     * @param stem Stem to be searched
     * @return List of indices of [DictionaryEntry] for the given stem or empty list if nothing is found
     */
    fun binarySearch(stem: String): List<Int> {
        val result = mutableListOf<Int>()

        for (i in 0..3) {
            result.addAll(this.binarySearchDictionaryRange(stem, i))
        }

        return result.distinct()
    }

    /**
     * Finds all indices of a particular stem based on the provided stem-index.
     *
     * Since binary search can only find one index, it alone is not enough, because oftentimes there are multiple
     * stems. These stems are necessarily next to each other (since they are sorted), so after finding the index,
     * we do a linear search (which shouldn't have any major impact on performance) to pick up the rest of indices.
     *
     * @param stem Stem to be searched
     * @param index Stem-index (which stem in the dictionary) to be searched
     */
    private fun binarySearchDictionaryRange(stem: String, index: Int): List<Int> {
        val foundIndex = this.binarySearchDictionary(stem, index)
        if (foundIndex < 0) return emptyList()

        val dict = this.sortedStems[index]
        val result = mutableListOf(dict[foundIndex].second)

        // Since both sorting and binary search are stable, we only need to scan the left side
        var leftIndex = (foundIndex - 1).coerceAtLeast(0)

        while (leftIndex >= 0 && dict[leftIndex].first == stem) {
            result.add(dict[leftIndex].second)
            leftIndex--
        }

        return result
    }

    /*
        Code for a more efficient binary search adapted from:
        - https://github.com/scandum/binary_search

        (See monobound_quaternary_search() in binary_search.c)
     */
    private fun binarySearchDictionary(stem: String, index: Int): Int {

        if (stem.isEmpty()) return -1

        val dict = this.sortedStems[index]

        var low = 0
        var high = dict.size
        var mid: Int

        while (high >= 65536) {

            mid = high / 4
            high -= mid * 3

            if (stem < dict[low + mid * 2].first) {

                if (stem >= dict[low + mid].first) {

                    low += mid

                }

            } else {

                low += mid * 2

                if (stem >= dict[low + mid].first) {

                    low += mid

                }

            }

        }

        while (high > 3) {

            mid = high / 2

            if (stem >= dict[low + mid].first) {

                low += mid

            }

            high -= mid

        }

        while (high-- > 0) {

            if (stem == dict[low + high].first) {

                return low + high

            }

        }

        return -1

    }

    private fun sortEntries(index: Int): List<Pair<String, Int>> {
        if (index < 0) throw IllegalArgumentException("Index may not be negative!")
        if (index > 3) throw IllegalArgumentException("Index may not exceed 3 (maximum number of stems is 4)!")

        val result = mutableListOf<Pair<String, Int>>()

        for (indexedEntry in this.entries.withIndex()) {

            val stem = indexedEntry.value.stems.elementAtOrNull(index) ?: continue

            // Dirty trick to work with u/v and i/j
            val replacedValues = WordUtility.replaceIwithJ(WordUtility.replaceVwithU(stem))

            result.add(Pair(replacedValues, indexedEntry.index))

        }

        return result.sortedBy { pair -> pair.first }
    }

}