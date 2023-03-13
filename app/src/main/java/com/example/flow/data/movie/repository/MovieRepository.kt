package com.example.flow.data.movie.repository


import com.example.flow.data.movie.model.MovieResponse
import com.example.flow.data.movie.retrofit.RetrofitInstance
import retrofit2.Response

class MovieRepository {
    suspend fun getMovieResponses(
        clientId: String,
        clientPw: String,
        start: Int,
        s: String
    ): Response<MovieResponse> {
        return RetrofitInstance.movieApi.getMovieResponses(clientId, clientPw, start, s)
    }
}