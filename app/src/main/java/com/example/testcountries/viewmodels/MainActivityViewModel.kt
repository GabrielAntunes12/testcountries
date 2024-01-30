package com.example.testcountries.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testcountries.model.CountryData
import com.example.testcountries.repository.MainActivityRepository
import com.example.testcountries.util.TextUtils.removeAccents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Response
import java.text.Normalizer
import kotlin.coroutines.coroutineContext

class MainActivityViewModel(private val repository: MainActivityRepository) : ViewModel() {
    private var mSuccessCountry = MutableLiveData<StateScreens>()
    var successCountry: LiveData<StateScreens> = mSuccessCountry
    private var actualSearch = ""

    fun getCountry(countryName: String) {
        cleanActualSearch()
        actualSearch = countryName
        CoroutineScope(Dispatchers.Default).launch {
            treatmentReturn(repository.getCountry(countryName.replace("\\s".toRegex(), "")))
        }
    }

    fun getCountryByLanguage(languageName: String) {
        cleanActualSearch()
        CoroutineScope(Dispatchers.Default).launch {
            treatmentReturn(repository.getCountryByLanguageName(languageName))
        }
    }

    private fun cleanActualSearch() {
        actualSearch = ""
    }

    private fun treatmentReturn(it: retrofit2.Response<List<CountryData>>) {
        when (it.isSuccessful) {
            true -> mSuccessCountry.postValue(StateScreens.SuccessState(treatmentName(it.body() as List<CountryData>)))
            false -> mSuccessCountry.postValue(StateScreens.Error)
        }
    }

    private fun treatmentName(list: List<CountryData>): List<CountryData> {
        val treatList: List<CountryData> = list.filter {
            it.translations["por"]?.let { nativeDetails ->
                nativeDetails.common.removeAccents().lowercase()
                    .contains(actualSearch.removeAccents().lowercase().replace("\\s".toRegex(), ""))
            } == true
        }
        return treatList
    }
}

sealed class StateScreens{
    data class SuccessState(var list : List<CountryData>) : StateScreens()
    object Error : StateScreens()
}


