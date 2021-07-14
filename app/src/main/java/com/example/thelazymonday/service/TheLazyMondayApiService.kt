package com.example.thelazymonday.service

import com.example.thelazymonday.data.remote.response.GameNewsResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://the-lazy-media-api.vercel.app"

val loggingHttpInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

val client = OkHttpClient.Builder().addInterceptor(loggingHttpInterceptor).build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface TheLazyMondayApiService {
    @GET("$BASE_URL/api/games?page=1")
    suspend fun getGameNews(): GameNewsResponse
}

object TheLazyMondayApi {
    val theLazyMondayApiService: TheLazyMondayApiService by lazy {
        retrofit.create(TheLazyMondayApiService::class.java)
    }
}