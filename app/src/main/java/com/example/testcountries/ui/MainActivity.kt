package com.example.testcountries.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testcountries.R
import com.example.testcountries.interfaces.ClickDetailsInterface
import com.example.testcountries.databinding.ActivityMainBinding
import com.example.testcountries.model.CountryData
import com.example.testcountries.ui.adapter.ListCountriesAdapter
import com.example.testcountries.util.DETAILS_OBJECT_TAG
import com.example.testcountries.util.EnumsUtils
import com.example.testcountries.viewmodels.MainActivityViewModel
import com.example.testcountries.viewmodels.StateScreens
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.Serializable

class MainActivity : AppCompatActivity(), ClickDetailsInterface {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<MainActivityViewModel>()
    private val adapter = ListCountriesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupButtons()
        setupRecycler()
        setupObservers()
    }

    private fun setupRecycler() {
        binding.recyclerCountry.layoutManager = LinearLayoutManager(this)
        binding.recyclerCountry.adapter = adapter
    }

    private fun setupButtons() {
        binding.buttonTest.setOnClickListener {
            getApiCountry()
        }
        binding.buttonLanguages.setOnClickListener {
            showDialogCustom()
        }
    }

    private fun showDialogCustom() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.choose_language_title))

        builder.setItems(EnumsUtils.buildOptions()) { dialog, position ->
            getApiCountryByLanguage(EnumsUtils.Languages.values()[position].languageName)
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun getApiCountryByLanguage(languageName: String) {
        viewModel.getCountryByLanguage(languageName)
        showLoading()
    }

    private fun showLoading() {
        binding.loading.visibility = View.VISIBLE
        binding.editText.isEnabled = false
        binding.buttonLanguages.isEnabled = false
        binding.buttonTest.isEnabled = false
    }

    private fun dismissLoading() {
        binding.loading.visibility = View.GONE
        binding.editText.isEnabled = true
        binding.buttonLanguages.isEnabled = true
        binding.buttonTest.isEnabled = true
    }

    private fun getApiCountry() {
        viewModel.getCountry(binding.editText.text.toString())
        showLoading()
    }


    private fun setupObservers() {
        viewModel.successCountry.observe(this, {
            when (it) {
                StateScreens.Error -> showError()
                is StateScreens.SuccessState ->
                    if (it.list.isNotEmpty()) {
                        adapter.addList(it.list)
                        dismissLoading()
                    }else showError()
            }
        })
    }

    private fun showError() {
        Toast.makeText(this, getString(R.string.generic_error), Toast.LENGTH_SHORT).show()
        dismissLoading()
    }

    override fun onClickDetails(countryDetails: CountryData) {
        if (!binding.loading.isVisible) {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(DETAILS_OBJECT_TAG, countryDetails as Serializable)
            startActivity(intent)
        }
    }
}