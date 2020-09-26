package com.example.sleeptrackingapp.titleFragment

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sleeptrackingapp.database.SleepDatabaseDAO
import java.util.*


class TitleFragmentViewModelFactory(private val dataSource: SleepDatabaseDAO, private val application: Application):ViewModelProvider.Factory {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TitleFragmentViewModel::class.java)){
            return TitleFragmentViewModel(dataSource, application) as T
        }
        throw IllformedLocaleException("Unknown ViewModel Class")
    }

}