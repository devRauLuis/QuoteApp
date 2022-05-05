package com.devaruluis.mvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devaruluis.mvvm.domain.GetQuotesUseCase
import com.devaruluis.mvvm.domain.GetRandomQuoteUseCase
import com.devaruluis.mvvm.domain.model.Quote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase
) : ViewModel() {

    val quote = MutableLiveData<Quote?>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getQuotesUseCase()
            if (!result.isNullOrEmpty()) {
                quote.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }

    fun randomQuote() {
        viewModelScope.launch {
            isLoading.postValue(true)
            var randomQuote = getRandomQuoteUseCase()
            while (quote.value == randomQuote) {
                randomQuote = getRandomQuoteUseCase()
            }
            if (randomQuote != null)
                quote.postValue(randomQuote)

            isLoading.postValue(false)
        }
    }


}