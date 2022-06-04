package com.kimym.rickandmorty.presentation.episode

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
import com.kimym.rickandmorty.databinding.FragmentEpisodeBinding
import com.kimym.rickandmorty.presentation.adapter.EpisodeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodeFragment : BaseFragment<FragmentEpisodeBinding>(R.layout.fragment_episode) {
    private val viewModel by viewModels<EpisodeViewModel>()
    private val episodeAdapter by lazy { EpisodeAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initRvEpisode()
        initEpisodeCollect()
    }

    private fun initAdapter() {
        binding.adapter = episodeAdapter
        binding.executePendingBindings()
    }

    private fun initRvEpisode() {
        with(binding.rvEpisode) {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }
    }

    private fun initEpisodeCollect() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.episodes.collectLatest {
                        episodeAdapter.submitData(it)
                    }
                }
                launch {
                    episodeAdapter.loadStateFlow.collect { loadState ->
                        binding.loadState = loadState
                        binding.executePendingBindings()
                    }
                }
            }
        }
    }
}
