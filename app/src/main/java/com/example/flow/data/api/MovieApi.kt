package com.example.flow.data.api

import com.example.flow.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("search/movie.json")
    suspend fun getMovieResponses(
        @Header("X-Naver-Client-Id") id : String,
        @Header("X-Naver-Client-Secret") pw: String,
        @Query("start") start: Int,
        @Query("query") query: String
    ): Response<MovieResponse>
}
