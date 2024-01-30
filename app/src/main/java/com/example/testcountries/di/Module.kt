package com.example.testcountries.di

import android.app.Application
import com.example.testcountries.repository.MainActivityRepository
import com.example.testcountries.service.CountryService
import com.example.testcountries.viewmodels.MainActivityViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val netModule = module {
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://restcountries.com/v3.1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }
    fun provideHttpClient(cache: Cache): OkHttpClient{
        val okHttpClientBuilder = OkHttpClient.Builder()
            .cache(cache)

        return okHttpClientBuilder.build()
    }

    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    fun provideGson(): Gson = GsonBuilder().create()

    single { provideCache(androidApplication()) }
    single { provideHttpClient(get()) }
    single { provideGson() }
    single { provideRetrofit(get(), get()) }
}

val apiModule = module {
    fun provideCountryApi(retrofit: Retrofit) : CountryService{
        return retrofit.create(CountryService::class.java)
    }

    single { provideCountryApi(get()) }
}

val viewModelModule = module {
    viewModel<MainActivityViewModel>()
}

val repositoryModule = module {
    fun provideUserRepository(api: CountryService): MainActivityRepository{
        return MainActivityRepository(api)
    }
    single { provideUserRepository(get()) }
}