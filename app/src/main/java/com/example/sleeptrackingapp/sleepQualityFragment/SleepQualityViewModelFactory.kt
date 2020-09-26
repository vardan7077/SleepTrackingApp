package com.example.sleeptrackingapp.sleepQualityFragment

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sleeptrackingapp.database.SleepDatabaseDAO
import java.util.*

class SleepQualityViewModelFactory(private val sleepNightKey:Long, val database: SleepDatabaseDAO):ViewModelProvider.Factory{
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SleepQualityViewModel::class.java)){
            return SleepQualityViewModel(sleepNightKey, database) as T
        }
        throw IllformedLocaleException("Unknown ViewModel Class")
    }
}