package com.example.flow.data.api

import com.example.flow.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("/")
    fun getMovieResponses(
        @Header("X-Naver-Client-Id") id : String,
        @Header("X-Naver-Client-Secret") pw: String,
        //@Path("type") type: String,
        @Query("query") query: String
    ): Response<MovieResponse>
}