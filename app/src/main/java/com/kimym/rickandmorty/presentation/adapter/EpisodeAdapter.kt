package com.kimym.rickandmorty.presentation.adapter

import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import com.kimym.rickandmorty.BR
import com.kimym.rickandmorty.R
import com.kimym.rickandmorty.base.BaseRickAndMortyPagingAdapter
import com.kimym.rickandmorty.data.entity.SealedRickAndMortyEntity
import com.kimym.rickandmorty.presentation.episode.EpisodeFragmentDirections

class EpisodeAdapter : BaseRickAndMortyPagingAdapter() {
    override fun getViewType(): Int {
        return R.layout.item_episode
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        getItem(position)?.let {
            val episode = it as SealedRickAndMortyEntity.EpisodeEntity
            binding.setVariable(BR.episode, episode.entity)
            binding.root.setOnClickListener { view ->
                val directions = EpisodeFragmentDirections
                val action = directions.actionEpisodeToEpisodeDetail(episode.entity)
                view.findNavController().navigate(action)
            }
        }
    }
}
