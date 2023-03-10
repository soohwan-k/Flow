package com.example.flow.data.repository


import com.example.flow.data.model.MovieResponse
import com.example.flow.data.retrofit.RetrofitInstance
import retrofit2.Response

class MovieRepository {

    suspend fun getMovieResponses(clientId: String, clientPw: String, s: String): Response<MovieResponse> {
        return RetrofitInstance.movieApi.getMovieResponses(clientId, clientPw, s)
    }
}