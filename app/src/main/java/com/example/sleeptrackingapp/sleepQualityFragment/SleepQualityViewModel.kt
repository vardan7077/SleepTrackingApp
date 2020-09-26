package com.example.sleeptrackingapp.sleepQualityFragment

import android.util.Log
import androidx.lifecycle.ViewModel

class SleepQualityViewModel(private val num:Int): ViewModel() {
    init {
        Log.i("Sleep", "$num")
    }
}