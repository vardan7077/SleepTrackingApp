package com.example.sleeptrackingapp.eachItemFragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sleeptrackingapp.database.SleepDatabaseDAO
import com.example.sleeptrackingapp.database.SleepNight
import com.example.sleeptrackingapp.titleFragment.TitleFragmentViewModel
import java.util.*

class EachItemFragmentViewModelFactory(val database:SleepDatabaseDAO, val nightID: Long, val application: Application):ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EachItemFragmentViewModel::class.java)) {
            return EachItemFragmentViewModel(database, nightID, application) as T
        }
        throw IllformedLocaleException("Unknown ViewModel Class")
    }
}