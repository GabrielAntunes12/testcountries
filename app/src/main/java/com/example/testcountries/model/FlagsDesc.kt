package com.example.testcountries.model

import java.io.Serializable

data class  FlagsDesc(
    var png : String = "",
    var svg : String = "",
    var alt : String = ""
) : Serializable
