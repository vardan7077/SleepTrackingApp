package com.example.sleeptrackingapp.titleFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sleeptrackingapp.R

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sleeptrackingapp.database.SleepDatabase
import com.example.sleeptrackingapp.databinding.FragmentTitleBinding
import com.google.android.material.snackbar.Snackbar


class TitleFragment : Fragment() {
    private lateinit var binding: FragmentTitleBinding
    private lateinit var viewModel:TitleFragmentViewModel
    private lateinit var viewModelFactory: TitleFragmentViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title, container, false)


        val application = requireNotNull(this.activity).application
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDAO

        viewModelFactory = TitleFragmentViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(TitleFragmentViewModel::class.java)

        binding.titleFragmentViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.navigateToSleepQuality.observe(this, Observer{
            night->
            night?.let{
                findNavController().navigate(TitleFragmentDirections.actionMainFragmentToSleepQuality(night.nightId))
                viewModel.doneNavigating()
            }
        })

        viewModel.showSnackBar.observe(this, Observer {
            if(it == true){
                Snackbar.make(activity!!.findViewById(android.R.id.content), getString(R.string.cleared_message), Snackbar.LENGTH_SHORT).show()
                viewModel.doneSnackBar()
            }
        })
        return binding.root
    }

}