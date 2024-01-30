package com.example.testcountries.util


const val PORTUGUESE_TAG = "por"
object EnumsUtils {
    enum class Languages(val languageName : String,val translatedName : String){
        ENGLISH("english", "Inglês"),
        FRENCH("french", "Francês"),
        ARABIC("arabic", "Árabe"),
        SPANISH("spanish", "Espanhol"),
        PORTUGUESE("portuguese", "Português"),
        DUTCH("dutch", "Holandês"),
        CHINESE("chinese", "Chinês"),
        GERMAN("german","Alemão"),
        ITALIAN("italian", "Italiano"),
        JAPANESE("japanese", "Japonês"),
        RUSSIAN("russian", "Russo")

    }

    fun buildOptions(): Array<String>{
        val listOptions = mutableListOf<String>()
        Languages.values().map {
            listOptions.add(it.translatedName)
        }
        return listOptions.toTypedArray()
    }
}