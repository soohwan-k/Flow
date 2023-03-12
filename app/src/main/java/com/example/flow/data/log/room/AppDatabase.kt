package com.example.flow.data.log.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.flow.data.log.dao.SearchLogDao
import com.example.flow.data.log.model.SearchLog


@Database(entities = [SearchLog::class],version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun searchLogDao(): SearchLogDao
    companion object{
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context: Context): AppDatabase?{
            if(INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java,"searchLog.db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}