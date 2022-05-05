package com.devaruluis.mvvm.domain.model

import com.devaruluis.mvvm.data.database.entities.QuoteEntity
import com.devaruluis.mvvm.data.model.QuoteModel

data class Quote(val quote: String?, val author: String?)

fun QuoteModel.toDomain() = Quote(quote, author)

fun QuoteEntity.toDomain() = Quote(quote, author)