package com.devaruluis.mvvm.data.model

import com.google.gson.annotations.SerializedName

data class ColorsModel(
   @SerializedName("colors") val colors: List<ColorModel>
)