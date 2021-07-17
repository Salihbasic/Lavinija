package com.github.salihbasic.lavinija.search

/**
 * Set of utility functions for various word manipulations.
 */
object WordUtility {

    private val uppercasePattern = Regex("[A-Z]")
    private val punctuationPattern = Regex("[.,/#!\$%^&*;:{}=\\-_`~()\\[\\]?\"<>]+")
    private val extraWhitespace = Regex("\\s{2,}")

    /**
     * Strips off the usual punctuation and extra whitespace from a given word thus making it usable
     *
     * Punctuation can easily affect dictionary search results, so stripping it is always a good idea.
     *
     * @param word Word to strip punctuation and whitespace from
     * @return Word searchable by a dictionary
     */
    fun stripPunctuation(word: String): String {
        return word.replace(punctuationPattern, "").replace(extraWhitespace, " ")
    }

    fun containsUppercase(word: String): Boolean = word.contains(uppercasePattern)

    fun replaceIwithJ(word: String): String = word.replace('i', 'j').replace('I', 'J')

    fun replaceVwithU(word: String): String = word.replace('v', 'u').replace('V', 'U')




}