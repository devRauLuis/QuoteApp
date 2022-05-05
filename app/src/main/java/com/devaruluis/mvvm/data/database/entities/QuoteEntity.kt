package com.devaruluis.mvvm.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.devaruluis.mvvm.domain.model.Quote

@Entity(tableName = "quote_table")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "quote")
    val quote: String?,
    @ColumnInfo(name = "author")
    val author: String?
)

fun Quote.toDatabase() = QuoteEntity(quote = quote, author = author)