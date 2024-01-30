package com.example.testcountries.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testcountries.interfaces.ClickDetailsInterface
import com.example.testcountries.databinding.ItemListCountryBinding
import com.example.testcountries.model.CountryData

class ListCountriesAdapter(var clickDetailsInterface: ClickDetailsInterface) : RecyclerView.Adapter<ListCountriesViewHolder>() {
    private var listCountries: MutableList<CountryData> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCountriesViewHolder {
        val itemView = ItemListCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListCountriesViewHolder(itemView, clickDetailsInterface)
    }

    override fun onBindViewHolder(holder: ListCountriesViewHolder, position: Int) {
        holder.bind(listCountries[position])
    }

    override fun getItemCount() = listCountries.size

    fun addList(listCountriesAdd : List<CountryData>){
        listCountries.clear()
        listCountries.addAll(listCountriesAdd)
        notifyDataSetChanged()
    }
}