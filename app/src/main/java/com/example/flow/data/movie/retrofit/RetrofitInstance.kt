package com.example.flow.data.movie.retrofit

import com.example.flow.Constants.Companion.BASE_URL
import com.example.flow.data.movie.api.MovieApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    //var gson = GsonBuilder().setLenient().create()
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    val movieApi: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }
}