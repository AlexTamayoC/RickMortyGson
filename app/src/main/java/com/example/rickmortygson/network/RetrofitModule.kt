package com.example.rickmortygson.network

import com.example.rickmortygson.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitModule {

    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    private fun getLoggingInterceptor(): Interceptor {
        val level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return HttpLoggingInterceptor().setLevel(level)
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(getLoggingInterceptor()).build()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().client(getOkHttpClient()).baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun createAPI(): CharacterAPI {
        return getRetrofit().create(CharacterAPI::class.java)
    }
}