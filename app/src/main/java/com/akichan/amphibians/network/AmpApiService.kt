package com.akichan.amphibians.network

import com.akichan.amphibians.model.AmpItems
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface AmpApiService {
    @GET("amphibians")
    suspend fun getAmps(): List<AmpItems>
}

object AmpApi {
    val retrofitService : AmpApiService by lazy {
        retrofit.create(AmpApiService::class.java)
    }
}