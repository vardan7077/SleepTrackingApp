package com.example.sleeptrackingapp.eachItemFragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sleeptrackingapp.convertDurationToFormatted
import com.example.sleeptrackingapp.convertNumericQualityToString
import com.example.sleeptrackingapp.database.SleepDatabaseDAO
import com.example.sleeptrackingapp.database.SleepNight
import kotlinx.coroutines.*

class EachItemFragmentViewModel(val database: SleepDatabaseDAO, val nightID:Long, application: Application):AndroidViewModel(application) {
    val viewModelJob = Job()
    val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var _night = MutableLiveData<SleepNight>()
    val night:LiveData<SleepNight>
        get() = _night


    var sleepNightString = MutableLiveData<String>()

    var sleepQualityString = MutableLiveData<String>()

    init {
        sleepNightString.value = "Hello!"
        sleepQualityString.value = "Hi!"
        getNight()

    }




    private fun getNight(){
        uiScope.launch {
            _night.value = getNightInto()
            getStrings()
        }
    }

    private suspend fun getNightInto():SleepNight?{
        return withContext(Dispatchers.IO){
             var night = database.get(nightID)
             night
        }
    }

    private fun getStrings(){

        var res = getApplication<Application>().resources
        if(night.value != null) {
            sleepNightString.value = convertDurationToFormatted(night.value!!.startTimeMilli, night.value!!.endTimeMilli, res)
            sleepQualityString.value = convertNumericQualityToString(night.value!!.sleepQuality, res)
        }
        else{
            sleepNightString.value = _night.value.toString()
            sleepQualityString.value = night.toString()
        }

    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}