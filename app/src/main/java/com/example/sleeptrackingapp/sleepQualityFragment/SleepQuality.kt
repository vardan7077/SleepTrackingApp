package com.example.sleeptrackingapp.sleepQualityFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sleeptrackingapp.R
import com.example.sleeptrackingapp.database.SleepDatabase
import com.example.sleeptrackingapp.database.SleepDatabaseDAO
import com.example.sleeptrackingapp.databinding.FragmentSleepQualityBinding



class SleepQuality : Fragment() {
    private lateinit var binding:FragmentSleepQualityBinding
    private lateinit var viewModel:SleepQualityViewModel
    private lateinit var viewModelFactory:SleepQualityViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sleep_quality, container, false)
        val application = requireNotNull(this.activity).application
        val args = SleepQualityArgs.fromBundle(arguments!!)
        val database = SleepDatabase.getInstance(application).sleepDatabaseDAO
        viewModelFactory = SleepQualityViewModelFactory(args.sleepNightKey, database)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SleepQualityViewModel::class.java)
        binding.sleepQualityViewModel = viewModel
        viewModel.navigateToTitleFrament.observe(this, Observer {
            if(it == true){
                findNavController().navigate(SleepQualityDirections.actionSleepQualityToMainFragment())
                viewModel.doneNavigating()
            }
        })
        return binding.root
    }

}