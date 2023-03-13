package com.example.frog.network

import com.example.frog.data.FrogInfoItem
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

private var retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface frogApiService{
    @GET("amphibians")
    suspend fun getFrog():MutableList<FrogInfoItem>
}
object FrogApi{
    val retrofitApiService:frogApiService by lazy {
        retrofit.create(frogApiService::class.java)
    }
}