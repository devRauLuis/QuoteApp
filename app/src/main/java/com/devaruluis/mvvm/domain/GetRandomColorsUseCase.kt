package com.devaruluis.mvvm.domain

import com.devaruluis.mvvm.data.ColorRepository
import com.devaruluis.mvvm.data.model.ColorsModel
import javax.inject.Inject

class GetRandomColorsUseCase @Inject constructor(private val repository: ColorRepository) {
    suspend operator fun invoke(quantity: Int): ColorsModel = repository.getRandomColors(quantity)
}