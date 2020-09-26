package com.example.sleeptrackingapp.sleepQualityFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.sleeptrackingapp.R
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
        viewModelFactory = SleepQualityViewModelFactory(14)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SleepQualityViewModel::class.java)
        return binding.root
    }

}