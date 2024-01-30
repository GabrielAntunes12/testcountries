package com.example.testcountries.model

import java.io.Serializable

data class CountryData(
    var name : DetailNameCountry = DetailNameCountry(),
    var area : Double = 0.0,
    var languages : MutableMap<String, String> = mutableMapOf(),
    var continents : List<String> = listOf(),
    var flags : FlagsDesc = FlagsDesc(),
    var population : Int = 0,
    var translations : MutableMap<String,NativeNameDetails> = mutableMapOf()
) : Serializable