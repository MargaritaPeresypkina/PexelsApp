package com.example.pexelsapp.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.pexels.com/"
    private const val API_KEY = "9vkDewW4881IZe41UdBw5VdK0hPhLGEW841Hfl53XUKPQ4gRUbxLDjLa"

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", API_KEY)
                .build()
            chain.proceed(request)
        }
        .build()

    val api: PexelsApi by lazy {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PexelsApi::class.java)
    }

}
