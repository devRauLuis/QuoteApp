package com.devaruluis.mvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devaruluis.mvvm.data.model.ColorModel
import com.devaruluis.mvvm.domain.GetRandomColorUseCase
import com.devaruluis.mvvm.domain.GetRandomColorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColorViewModel @Inject constructor(
    private val getRandomColorsUseCase: GetRandomColorsUseCase,
    private val getRandomColorUseCase: GetRandomColorUseCase
) :
    ViewModel() {
    val colorModel = MutableLiveData<ColorModel?>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate(quantity: Int = 30) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getRandomColorsUseCase(quantity)
            if (!result.colors.isNullOrEmpty())
                colorModel.postValue(result.colors[0])
            isLoading.postValue(false)
        }
    }

    fun randomColor() {
        isLoading.postValue(true)
        val color = getRandomColorUseCase()

        if (color != null)
            colorModel.postValue(color)

        isLoading.postValue(false)
    }
}