package com.example.sleeptrackingapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [SleepNight::class], version = 1,  exportSchema = false)
abstract class SleepDatabase:RoomDatabase() {
    abstract val sleepDatabaseDAO: SleepDatabaseDAO

    companion object{
        @Volatile
        private var INSTANCE: SleepDatabase? = null
        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(context: Context): SleepDatabase{
            synchronized(this){
                var instance = INSTANCE
                    if(instance == null){
                        instance = Room.databaseBuilder(context.applicationContext, SleepDatabase::class.java, "sleep_history_database")
                            .fallbackToDestructiveMigration()
                            .build()
                        INSTANCE = instance
                    }
                return instance
            }

        }
    }
}