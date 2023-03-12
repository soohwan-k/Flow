package com.example.flow.presentation.log

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.flow.data.log.model.SearchLog
import com.example.flow.data.log.repository.SearchLogRepository

class SearchLogViewModel(application: Application) : ViewModel(){

    private val repository = SearchLogRepository(application)

    fun getAll(): LiveData<List<SearchLog>> {
        return repository.getAll()
    }

    fun insert(searchLog: SearchLog){
        repository.insert(searchLog)
    }

    fun deleteAll(){
        repository.deleteAll()
    }

    override fun onCleared() {
        super.onCleared()
    }
}