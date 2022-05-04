package com.devaruluis.mvvm.data

import com.devaruluis.mvvm.data.model.QuoteModel
import com.devaruluis.mvvm.data.model.QuoteProvider
import com.devaruluis.mvvm.data.network.QuoteService
import javax.inject.Inject

class QuoteRepository @Inject constructor(private val api: QuoteService) {

    suspend fun getAllQuotes(): List<QuoteModel> {
        val res = api.getQuotes()
        QuoteProvider.quotes = res
        return res
    }
}