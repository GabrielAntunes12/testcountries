package com.example.testcountries.model

import java.io.Serializable

data class DetailNameCountry(
    var common: String = "",
    var official: String= "",
    val nativeName : MutableMap<String, NativeNameDetails> = mutableMapOf()
) : Serializable