package com.example.sleeptrackingapp.sleepQualityFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sleeptrackingapp.database.SleepDatabaseDAO
import com.example.sleeptrackingapp.database.SleepNight
import kotlinx.coroutines.*

class SleepQualityViewModel(private val sleepNightKey:Long, val database:SleepDatabaseDAO): ViewModel() {
    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _navigateToTitleFragment = MutableLiveData<Boolean>()
        val navigateToTitleFrament: LiveData<Boolean>
            get() = _navigateToTitleFragment

    fun doneNavigating(){
        _navigateToTitleFragment.value = null
    }

    fun onSetSleepQuality(quality:Int){
        uiScope.launch {
            withContext(Dispatchers.IO) {
                val tonight= database.get(sleepNightKey) ?: return@withContext
                tonight.sleepQuality = quality
                database.update(tonight)

            }
            _navigateToTitleFragment.value = true
        }
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}