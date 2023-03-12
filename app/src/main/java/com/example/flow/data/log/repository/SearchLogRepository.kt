package com.example.flow.data.log.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.flow.data.log.dao.SearchLogDao
import com.example.flow.data.log.model.SearchLog
import com.example.flow.data.log.room.AppDatabase

class SearchLogRepository(application: Application) {
    private val db = AppDatabase.getInstance(application)!!
    private val searchLogDao: SearchLogDao = db.searchLogDao()
    private val searchLogs: LiveData<List<SearchLog>> = searchLogDao.getAll()

    fun getAll(): LiveData<List<SearchLog>> {
        return searchLogs
    }

    fun insert(searchLog: SearchLog) {
        try {
            val thread = Thread {
                searchLogDao.insert(searchLog)
            }
            thread.start()
        } catch (e: Exception) {
        }
    }

    fun deleteAll() {
        try {
            val thread = Thread {
                searchLogDao.deleteAll()
            }
            thread.start()
        } catch (e: Exception) {
        }
    }
}