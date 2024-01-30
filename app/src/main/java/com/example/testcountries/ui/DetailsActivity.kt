package com.example.testcountries.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import com.bumptech.glide.Glide
import com.example.testcountries.R
import com.example.testcountries.databinding.ActivityDetailsBinding
import com.example.testcountries.databinding.ActivityMainBinding
import com.example.testcountries.model.CountryData
import com.example.testcountries.util.DETAILS_OBJECT_TAG
import com.example.testcountries.util.PORTUGUESE_TAG
import com.example.testcountries.util.TextUtils.getHumanReadablePriceFromNumber
import java.util.*

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val detailsObject = intent.extras?.get(DETAILS_OBJECT_TAG) as CountryData
        setupBackButton()
        fillInfos(detailsObject)
    }

    private fun setupBackButton() {
        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun fillInfos(detailsObject: CountryData) {
        buildFlagImage(detailsObject)
        buildHeaderNames(detailsObject)
        buildArea(detailsObject.area.toString())
        buildContinents(detailsObject)
        buildLanguages(detailsObject)
        buildPopulation(getHumanReadablePriceFromNumber(detailsObject.population.toLong()))
    }

    private fun buildFlagImage(detailsObject: CountryData) {
        Glide.with(this).load(
            detailsObject.flags.png
        ).into(binding.flagImage)
    }

    private fun buildPopulation(populationString: String) {
        binding.containerPopulation.text = populationString
    }

    private fun buildLanguages(detailsObject: CountryData) {
        var stringLanguages = ""
        detailsObject.languages.values.map {
            stringLanguages += "\n$it"
        }
        binding.containerLanguage.text = stringLanguages
    }

    private fun buildContinents(detailsObject: CountryData) {
        var stringContinents = ""
        detailsObject.continents.map {
            stringContinents += "\n$it"
        }
        binding.containerContinent.text = stringContinents
    }

    private fun buildArea(areaString: String) {
        binding.containerArea.text = getString(R.string.area_filled, areaString)
    }


    private fun buildHeaderNames(detailsObject: CountryData) {
        binding.commonName.text = detailsObject.translations[PORTUGUESE_TAG]?.common
        binding.officialName.text = detailsObject.translations[PORTUGUESE_TAG]?.official
    }
}