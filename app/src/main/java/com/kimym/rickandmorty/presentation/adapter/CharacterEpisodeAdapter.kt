package com.kimym.rickandmorty.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kimym.rickandmorty.base.BaseDiffUtilItemCallback
import com.kimym.rickandmorty.data.entity.Episode
import com.kimym.rickandmorty.databinding.ItemCharacterEpisodeBinding

class CharacterEpisodeAdapter :
    ListAdapter<Episode.EpisodeEntity, CharacterEpisodeAdapter.CharacterEpisodeViewHolder>(
        BaseDiffUtilItemCallback(
            itemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
            contentsTheSame = { oldItem, newItem -> oldItem == newItem }
        )
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterEpisodeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterEpisodeBinding.inflate(inflater, parent, false)
        return CharacterEpisodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterEpisodeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CharacterEpisodeViewHolder(private val binding: ItemCharacterEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(episode: Episode.EpisodeEntity) {
            binding.episode = episode
            binding.executePendingBindings()
        }
    }
}
