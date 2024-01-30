package com.example.testcountries.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.testcountries.interfaces.ClickDetailsInterface
import com.example.testcountries.databinding.ItemListCountryBinding
import com.example.testcountries.model.CountryData
import com.example.testcountries.util.PORTUGUESE_TAG

class ListCountriesViewHolder(
    private val binding: ItemListCountryBinding,
    private val clickDetailsInterface: ClickDetailsInterface
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CountryData) {
        var showBrNameCommon = item.name.common
        var showBrNameOfficial = item.name.official
        item.translations[PORTUGUESE_TAG]?.let {
            showBrNameCommon = it.common
            showBrNameOfficial = it.official
        }
        binding.countryName.text = showBrNameCommon
        binding.officialCountryName.text = showBrNameOfficial
        binding.containerClick.setOnClickListener {
            clickDetailsInterface.onClickDetails(item)
        }
    }

}