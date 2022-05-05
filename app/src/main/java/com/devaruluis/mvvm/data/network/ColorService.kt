package com.devaruluis.mvvm.data.network

import com.devaruluis.mvvm.data.model.ColorsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ColorService @Inject constructor(private val api: ColrApiClient) {
    suspend fun getRandomColor(quantity: Int): ColorsModel {
        return withContext(Dispatchers.IO) {
            val response = api.getRandomColors(quantity)
            response.body() ?: ColorsModel(emptyList())
        }
    }
}