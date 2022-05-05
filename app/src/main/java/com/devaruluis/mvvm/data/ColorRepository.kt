package com.devaruluis.mvvm.data

import com.devaruluis.mvvm.data.model.ColorProvider
import com.devaruluis.mvvm.data.model.ColorsModel
import com.devaruluis.mvvm.data.network.ColorService
import javax.inject.Inject

class ColorRepository @Inject constructor(private val api: ColorService) {
    suspend fun getRandomColors(quantity: Int): ColorsModel {
        val res = api.getRandomColor(quantity)
        ColorProvider.colors = res
        return res
    }
}