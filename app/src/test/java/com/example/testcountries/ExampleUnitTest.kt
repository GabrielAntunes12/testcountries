package com.example.testcountries

import com.example.testcountries.util.EnumsUtils
import com.example.testcountries.util.TextUtils.removeAccents
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun testTextUtilRemoveAccents() {
        val stringTest = "áéíóú"
        assertEquals("aeiou", stringTest.removeAccents())
    }
}