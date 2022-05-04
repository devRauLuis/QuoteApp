package com.devaruluis.mvvm.data.model

import javax.inject.Singleton

@Singleton
class QuoteProvider {
    companion object {
        var quotes: List<QuoteModel> = emptyList()
    }
}