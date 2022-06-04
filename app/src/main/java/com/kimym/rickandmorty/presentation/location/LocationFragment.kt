package com.kimym.rickandmorty.presentation.location

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.kimym.rickandmorty.R
import com.kimym.rickandmorty.base.BaseFragment
import com.kimym.rickandmorty.databinding.FragmentLocationBinding
import com.kimym.rickandmorty.presentation.adapter.LocationAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationFragment : BaseFragment<FragmentLocationBinding>(R.layout.fragment_location) {
    private val viewModel by viewModels<LocationViewModel>()
    private val locationAdapter by lazy { LocationAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initRvLocation()
        initLocationsCollect()
    }

    private fun initAdapter() {
        binding.adapter = locationAdapter
        binding.executePendingBindings()
    }

    private fun initRvLocation() {
        with(binding.rvLocation) {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }
    }

    private fun initLocationsCollect() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.locations.collectLatest {
                        locationAdapter.submitData(it)
                    }
                }
                launch {
                    locationAdapter.loadStateFlow.collect { loadState ->
                        binding.loadState = loadState
                        binding.executePendingBindings()
                    }
                }
            }
        }
    }
}
