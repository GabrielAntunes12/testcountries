package com.example.testcountries.model

import java.io.Serializable

data class NativeNameDetails(
    var official : String,
    var common : String
): Serializable
