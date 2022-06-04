package com.kimym.rickandmorty.presentation.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.kimym.rickandmorty.R
import com.kimym.rickandmorty.base.BaseFragment
import com.kimym.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.kimym.rickandmorty.presentation.adapter.CharacterEpisodeAdapter
import com.kimym.rickandmorty.util.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CharacterDetailFragment :
    BaseFragment<FragmentCharacterDetailBinding>(R.layout.fragment_character_detail) {
    private val viewModel by viewModels<CharacterDetailViewModel>()
    private val args by navArgs<CharacterDetailFragmentArgs>()
    private val episodeAdapter by lazy { CharacterEpisodeAdapter() }

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
        initRvEpisodes()
        initEpisodesCollect()
        viewModel.initCharacter(args.character)
    }

    private fun initRvEpisodes() {
        with(binding.rvEpisodes) {
            adapter = episodeAdapter
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }
    }

    private fun initEpisodesCollect() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.episodes
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .filter { it.status == Status.SUCCESS }
                .collect {
                    episodeAdapter.submitList(it.data)
                }
        }
    }
}
