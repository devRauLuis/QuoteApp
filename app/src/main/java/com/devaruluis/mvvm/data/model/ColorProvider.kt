package com.devaruluis.mvvm.data.model

import com.google.gson.annotations.SerializedName
import javax.inject.Singleton

@Singleton
class ColorProvider {
    companion object {
        @SerializedName("colors")
        var colors: ColorsModel = ColorsModel(emptyList())
    }
}