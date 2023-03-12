package com.example.flow.data.log.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.flow.data.log.model.SearchLog

@Dao
interface SearchLogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(searchLog: SearchLog)

    @Query("SELECT * FROM SearchLog")
    fun getAll(): LiveData<List<SearchLog>>

    @Query("DELETE FROM SearchLog")
    fun deleteAll()
}