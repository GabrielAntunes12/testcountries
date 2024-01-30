package com.example.testcountries.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.testcountries.interfaces.ClickDetailsInterface
import com.example.testcountries.databinding.ItemListCountryBinding
import com.example.testcountries.model.CountryData

class ListCountriesViewHolder(
    private val binding: ItemListCountryBinding,
    private val clickDetailsInterface: ClickDetailsInterface
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CountryData) {
        var showBrName = item.name.common
        item.translations["por"]?.let {
            showBrName = it.common
        }
        binding.countryName.text = showBrName
        binding.containerClick.setOnClickListener {
            clickDetailsInterface.onClickDetails(item)
        }
    }

}