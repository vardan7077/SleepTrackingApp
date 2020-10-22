package com.example.sleeptrackingapp.titleFragment

import android.app.Application
import android.text.Spanned
import android.util.Log
import androidx.lifecycle.*
import com.example.sleeptrackingapp.database.SleepDatabaseDAO
import com.example.sleeptrackingapp.database.SleepNight
import com.example.sleeptrackingapp.formatNights
import kotlinx.coroutines.*

class TitleFragmentViewModel(val database:SleepDatabaseDAO, application: Application):AndroidViewModel(application) {
    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var tonight = MutableLiveData<SleepNight?>()

    val nights = database.getAllNights()

    private var _navigateToSleepQuality = MutableLiveData<SleepNight>()
    val navigateToSleepQuality: LiveData<SleepNight>
            get() = _navigateToSleepQuality

    private var _showSnackBar = MutableLiveData<Boolean>()
    val showSnackBar: LiveData<Boolean>
        get() = _showSnackBar

    init{
        initializeTonight()
    }

    private fun initializeTonight(){
        uiScope.launch {
            tonight.value = getTonightFromDatabase()
        }
    }

    private suspend fun getTonightFromDatabase():SleepNight?{
        return withContext(Dispatchers.IO){
            var night = database.getTonight()
            if(night?.startTimeMilli != night?.endTimeMilli){
                night = null
            }
            night
        }
    }

    fun onStartTracking(){
        uiScope.launch {
            val newNight = SleepNight()
            insert(newNight)
            tonight.value = getTonightFromDatabase()
        }
    }

    private suspend fun insert(night: SleepNight){
        withContext(Dispatchers.IO){
            database.insert(night)
        }
    }

    fun doneNavigating() {
        _navigateToSleepQuality.value = null
    }

    fun onStopTracking(){
        uiScope.launch {
            val oldNight = tonight.value ?:return@launch
            oldNight.endTimeMilli = System.currentTimeMillis()
            update(oldNight)
            _navigateToSleepQuality.value = oldNight
        }
    }

    private suspend fun update(night: SleepNight){
        withContext(Dispatchers.IO){
            database.update(night)
        }
    }

    fun onClear(){
        uiScope.launch {
            clear()
            tonight.value = null
            _showSnackBar.value = true
        }
    }

    private suspend fun clear(){
        withContext(Dispatchers.IO){
            database.clear()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    val nightsString: LiveData<Spanned> = Transformations.map(nights) { nights ->
        formatNights(nights, application.resources)
    }

    val startButtonVisible = Transformations.map(tonight){
        null == it
    }
    val stopButtonVisible = Transformations.map(tonight){
        null != it
    }
    val clearButtonVisible = Transformations.map(nights){
        it?.isNotEmpty()
    }

    fun doneSnackBar(){
        _showSnackBar.value = false
    }

}