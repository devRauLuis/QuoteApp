package com.devaruluis.mvvm.domain

import com.devaruluis.mvvm.data.model.ColorModel
import com.devaruluis.mvvm.data.model.ColorProvider
import javax.inject.Inject

class GetRandomColorUseCase @Inject constructor() {
    operator fun invoke(): ColorModel? {
        val colors = ColorProvider.colors.colors
        if (!colors.isNullOrEmpty()) {
            val randomNumber = (colors.indices).random()
            return colors[randomNumber]
        }
        return null
    }
}