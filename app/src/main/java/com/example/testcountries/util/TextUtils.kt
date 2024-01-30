package com.example.testcountries.util

import java.text.Normalizer

object TextUtils {
    private val REGEX_UNACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()

    fun String.removeAccents(): String {
        val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
        return REGEX_UNACCENT.replace(temp, "")
    }

    fun getHumanReadablePriceFromNumber(number: Long): String {
        if (number >= 1000000000) {
            return String.format("%.2f Bilhões", number / 1000000000.0)
        }
        if (number >= 1000000) {
            return String.format("%.2f Milhões", number / 1000000.0)
        }
        if (number >= 100000) {
            return String.format("%.2fMil", number / 100000.0)
        }
        return if (number >= 1000) {
            String.format("%.2fMil", number / 1000.0)
        } else number.toString()
    }
}