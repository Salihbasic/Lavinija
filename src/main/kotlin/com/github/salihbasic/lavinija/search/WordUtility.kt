package com.github.salihbasic.lavinija.search

/**
 * Set of utility functions for various word manipulations.
 */
object WordUtility {

    private val uppercasePattern = Regex("[A-Z]")
    private val punctuationPattern = Regex("[.,/#!\$%^&*;:{}=\\-_`~()\\[\\]?\"<>]+")
    private val extraWhitespace = Regex("\\s{2,}")

    // We have to include both u and v
    private val checkIBeforeVowel = Regex("i(?=[aeiouv])")

    private val vowels = setOf(
        "a",
        "e",
        "i",
        "o",
        "u",
        "y"
    )

    fun isVowel(letter: String): Boolean = letter in this.vowels

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

    /**
     * Checks whether the provided word contains at least one uppercase character
     *
     * @param word Word which may have an uppercase character
     * @return `true` if the word contains at least one uppercase character
     */
    fun containsUppercase(word: String): Boolean {
        return word.contains(uppercasePattern)
    }


    fun replaceIwithJ(word: String): String {

        val startReplaced = when (word[0]) {
            'i' -> word.replaceFirst('i', 'j')
            'I' -> word.replaceFirst('I', 'J')
            else -> word
        }

        // Except last vowel
        return startReplaced.replace(checkIBeforeVowel, "j")

    }

    fun replaceVwithU(word: String): String {
        // Don't replace starting v/V
        return word.replace("v", "u").replace("V", "U")
    }



}