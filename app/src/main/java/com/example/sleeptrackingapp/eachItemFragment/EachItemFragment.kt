package com.example.sleeptrackingapp.eachItemFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sleeptrackingapp.R
import com.example.sleeptrackingapp.database.SleepDatabase
import com.example.sleeptrackingapp.database.SleepDatabaseDAO
import com.example.sleeptrackingapp.databinding.FragmentEachItemBinding


class EachItemFragment : Fragment() {
    private lateinit var  binding:FragmentEachItemBinding
    private lateinit var viewModel:EachItemFragmentViewModel
    private lateinit var viewModelFactory: EachItemFragmentViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_each_item, container, false)

        val application = requireNotNull(this.activity).application
        val database = SleepDatabase.getInstance(application).sleepDatabaseDAO

        var args = EachItemFragmentArgs.fromBundle(arguments!!)
        viewModelFactory = EachItemFragmentViewModelFactory(database, args.nightID, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(EachItemFragmentViewModel::class.java)

        viewModel.sleepQualityString.observe(viewLifecycleOwner, Observer {
            binding.sleepNight.text = viewModel.sleepNightString.value
            binding.sleepQuality.text = viewModel.sleepQualityString.value
        })

        return binding.root
    }

}