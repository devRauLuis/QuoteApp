package com.devaruluis.mvvm.di

import com.devaruluis.mvvm.data.network.ColrApiClient
import com.devaruluis.mvvm.data.network.QuoteApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val ZEN_QUOTES_API_URL = "https://zenquotes.io/"
    private const val COLR_API_URL = "https://www.colr.org/"

    @Provides
    @Named("zenQuotesGson")
    fun provideZenQuotesGson(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Named("colrGson")
    fun provideColrGson(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient().newBuilder()
            .addInterceptor(logging).build()
    }

    @Singleton
    @Provides
    @Named("zenQuotesApiRetrofit")
    fun provideZenQuotesApiRetrofit(
        @Named("zenQuotesGson") gson: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder().baseUrl(ZEN_QUOTES_API_URL)
            .addConverterFactory(gson).client(okHttpClient).build()

    @Singleton
    @Provides
    @Named("colrApiRetrofit")
    fun provideColrApiRetrofit(
        @Named("colrGson") gson: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder().baseUrl(
        COLR_API_URL
    ).addConverterFactory(gson).client(okHttpClient).build()

    @Provides
    @Singleton
    fun provideQuoteApiClient(@Named("zenQuotesApiRetrofit") retrofit: Retrofit): QuoteApiClient {
        return retrofit.create(QuoteApiClient::class.java)
    }

    @Provides
    @Singleton
    fun provideColrApiClient(@Named("colrApiRetrofit") retrofit: Retrofit): ColrApiClient {
        return retrofit.create(ColrApiClient::class.java)
    }
}