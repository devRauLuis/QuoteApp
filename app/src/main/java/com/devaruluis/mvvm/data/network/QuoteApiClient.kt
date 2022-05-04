package com.devaruluis.mvvm.data.network

import com.devaruluis.mvvm.data.model.QuoteModel
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApiClient {
    @GET("/api/quotes/")
    suspend fun getAllQuotes(): Response<List<QuoteModel>>
}