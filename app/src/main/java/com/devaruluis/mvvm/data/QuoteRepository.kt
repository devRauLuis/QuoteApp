package com.devaruluis.mvvm.data

import com.devaruluis.mvvm.data.database.dao.QuoteDao
import com.devaruluis.mvvm.data.database.entities.QuoteEntity
import com.devaruluis.mvvm.data.network.QuoteService
import com.devaruluis.mvvm.domain.model.Quote
import com.devaruluis.mvvm.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteDao: QuoteDao
) {

    suspend fun getAllQuotesFromApi(): List<Quote> {
        val res = api.getQuotes()
        return res.map { it.toDomain() }
    }

    suspend fun getAllQuotesFromDatabase(): List<Quote> {
        val res = quoteDao.getAllQuotes()
        return res.map { it.toDomain() }
    }

    suspend fun insertQuotes(quotes: List<QuoteEntity>) {
        quoteDao.insertAll(quotes)
    }

    suspend fun clearQuotes() {
        quoteDao.deleteAllQuotes()
    }
}