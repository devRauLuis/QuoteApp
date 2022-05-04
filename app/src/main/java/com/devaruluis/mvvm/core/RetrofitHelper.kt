package com.devaruluis.mvvm.core

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {

    fun getRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient().newBuilder()
            .addInterceptor(logging)

        return Retrofit.Builder().baseUrl("https://zenquotes.io/")
            .addConverterFactory(GsonConverterFactory.create()).client(client.build()).build()
    }
}