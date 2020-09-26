package com.example.sleeptrackingapp.sleepQualityFragment

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.util.*

class SleepQualityViewModelFactory(private val num:Int):ViewModelProvider.Factory{
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SleepQualityViewModel::class.java)){
            return SleepQualityViewModel(num) as T
        }
        throw IllformedLocaleException("Unknown ViewModel Class")
    }
}