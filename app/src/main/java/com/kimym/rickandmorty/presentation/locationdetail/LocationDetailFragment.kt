package com.kimym.rickandmorty.presentation.locationdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.kimym.rickandmorty.R
import com.kimym.rickandmorty.base.BaseFragment
import com.kimym.rickandmorty.databinding.FragmentLocationDetailBinding
import com.kimym.rickandmorty.presentation.adapter.CharacterGridAdapter
import com.kimym.rickandmorty.util.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LocationDetailFragment :
    BaseFragment<FragmentLocationDetailBinding>(R.layout.fragment_location_detail) {
    private val viewModel by viewModels<LocationDetailViewModel>()
    private val args by navArgs<LocationDetailFragmentArgs>()
    private val adapter by lazy { CharacterGridAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRvResidents()
        initResidentsCollect()
        viewModel.initLocation(args.location)
    }

    private fun initRvResidents() {
        binding.rvResidents.adapter = adapter
        binding.rvResidents.setHasFixedSize(true)
    }

    private fun initResidentsCollect() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.residents
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .filter { it.status == Status.SUCCESS }
                .collect {
                    adapter.submitList(it.data)
                }
        }
    }
}
