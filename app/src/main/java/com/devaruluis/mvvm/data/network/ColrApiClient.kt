package com.devaruluis.mvvm.data.network

import com.devaruluis.mvvm.data.model.ColorsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ColrApiClient {
    @GET("json/colors/random/{quantity}")
    suspend fun getRandomColors(@Path("quantity") quantity: Int): Response<ColorsModel>
}