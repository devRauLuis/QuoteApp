package com.devaruluis.mvvm.domain

import android.util.Log
import com.devaruluis.mvvm.data.QuoteRepository
import com.devaruluis.mvvm.data.database.entities.toDatabase
import com.devaruluis.mvvm.domain.model.Quote
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(private val repository: QuoteRepository) {

    suspend operator fun invoke(): List<Quote> {
        val quotes = repository.getAllQuotesFromApi()
        return if (quotes.isNotEmpty()) {
            repository.clearQuotes()
            repository.insertQuotes(quotes.map { it.toDatabase() })
            Log.v("GetQuotesUseCase", quotes.toString())
            quotes
        } else {
            repository.getAllQuotesFromDatabase()
        }
    }

}