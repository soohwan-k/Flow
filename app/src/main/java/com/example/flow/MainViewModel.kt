package com.example.flow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flow.data.model.MovieResponse
import com.example.flow.data.repository.MovieRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val movieResponse: MutableLiveData<Response<MovieResponse>> = MutableLiveData()

    fun getMovieResponse(clientId: String, clientPw: String, s: String) {
        viewModelScope.launch {
            val response = movieRepository.getMovieResponses(clientId, clientPw, s)
            movieResponse.value = response
        }
    }
}