package com.example.testcountries.application

import android.app.Application
import com.example.testcountries.di.apiModule
import com.example.testcountries.di.netModule
import com.example.testcountries.di.repositoryModule
import com.example.testcountries.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CustomApplication)
            modules(listOf(netModule, apiModule, viewModelModule, repositoryModule))
        }
    }
}