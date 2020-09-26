package com.example.sleeptrackingapp.titleFragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sleeptrackingapp.database.SleepDatabaseDAO
import com.example.sleeptrackingapp.database.SleepNight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class TitleFragmentViewModel(val database:SleepDatabaseDAO, application: Application):AndroidViewModel(application) {
    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var tonight = MutableLiveData<SleepNight?>()

    private val nights = database.getAllNights()



    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}