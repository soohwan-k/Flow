package com.example.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flow.data.repository.MovieRepository


class MainViewModelFactory(private val movieRepository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(movieRepository) as T
    }
}