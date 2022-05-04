package com.devaruluis.mvvm.domain

import com.devaruluis.mvvm.data.QuoteRepository
import com.devaruluis.mvvm.data.model.QuoteModel
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(private val repository: QuoteRepository) {

    suspend operator fun invoke(): List<QuoteModel> = repository.getAllQuotes()

}