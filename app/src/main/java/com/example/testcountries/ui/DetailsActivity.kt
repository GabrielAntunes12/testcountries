package com.example.testcountries.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import com.bumptech.glide.Glide
import com.example.testcountries.databinding.ActivityDetailsBinding
import com.example.testcountries.databinding.ActivityMainBinding
import com.example.testcountries.model.CountryData
import com.example.testcountries.util.PORTUGUESE_TAG
import java.util.*

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val detailsObject = intent.extras?.get("details") as CountryData
        fillInfos(detailsObject)
    }

    private fun fillInfos(detailsObject: CountryData) {
        binding.commonName.text = detailsObject.translations[PORTUGUESE_TAG]?.common
        binding.officialName.text = detailsObject.translations[PORTUGUESE_TAG]?.official
        binding.area.text = detailsObject.area.toString() + " km"
        var stringContinents = ""
        detailsObject.continents.map {
            stringContinents += "\n$it"
        }
        binding.continent.text = stringContinents
        var stringLanguages = ""
        detailsObject.languages.values.map {
            stringLanguages += "\n$it"
        }
        binding.languages.text = stringLanguages
        binding.population.text = detailsObject.population.toString() + " população"
        Glide.with(this).load(
            detailsObject.flags.png
        ).into(binding.flagImage)
    }
}