package com.example.testcountries.repository

import com.example.testcountries.service.CountryService

class MainActivityRepository(private val countryService: CountryService) {

    suspend fun getCountry(countryName : String) = countryService.getCountry(countryName)

    suspend fun getCountryByLanguageName(languageName : String) = countryService.getCountryByLanguageName(languageName)

}