package com.example.flow.data.log.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SearchLog")
data class SearchLog(
    @PrimaryKey
    val searchLog: String
)
