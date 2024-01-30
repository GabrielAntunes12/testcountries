package com.example.testcountries.service

import com.example.testcountries.model.CountryData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryService {
    @GET("translation/{translation}")
    suspend fun getCountry(@Path("translation")country : String) : Response<List<CountryData>>

    @GET("lang/{language}")
    suspend fun getCountryByLanguageName(@Path("language")languageName : String) : Response<List<CountryData>>
}