package com.github.salihbasic.lavinija.search

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class WordUtilityTest {

    @Test
    fun `extra whitespace is stripped`() {
        val expected = "Amor vincit omnia"
        val test = "Amor     vincit omnia"

        assertEquals(expected, WordUtility.stripPunctuation(test))
    }

    @Test
    fun `all unnecessary punctuation stripped`() {
        val expected = "Gallia est omnis divisa in partes tres quarum unam incolunt Belgae aliam Aquitani"
        val test = "Gallia est omnis divisa in partes tres, quarum unam incolunt Belgae, aliam Aquitani..."
        val testMishmash = "Gallia(?) est! omnis!!! divisa!! in-#\"& partes<. tres, quarum unam incolunt Belgae, aliam Aquitani..."

        assertEquals(expected, WordUtility.stripPunctuation(test))
        assertEquals(expected, WordUtility.stripPunctuation(testMishmash))
    }

    @Test
    fun `recognises uppercase`() {
        val testStringAllCaps = "GALLIA"
        val testStringStartUp = "Achilles"
        val testStringRandomUp = "cuIus"

        assertTrue(WordUtility.containsUppercase(testStringAllCaps))
        assertTrue(WordUtility.containsUppercase(testStringStartUp))
        assertTrue(WordUtility.containsUppercase(testStringRandomUp))
    }

}