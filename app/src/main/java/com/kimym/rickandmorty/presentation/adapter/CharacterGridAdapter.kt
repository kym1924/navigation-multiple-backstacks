package com.kimym.rickandmorty.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kimym.rickandmorty.base.BaseDiffUtilItemCallback
import com.kimym.rickandmorty.data.entity.Character
import com.kimym.rickandmorty.databinding.ItemCharacterGridBinding

class CharacterGridAdapter :
    ListAdapter<Character.CharacterEntity, CharacterGridAdapter.SimpleCharacterViewHolder>(
        BaseDiffUtilItemCallback(
            itemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
            contentsTheSame = { oldItem, newItem -> oldItem == newItem }
        )
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleCharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterGridBinding.inflate(inflater, parent, false)
        return SimpleCharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimpleCharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SimpleCharacterViewHolder(private val binding: ItemCharacterGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character.CharacterEntity) {
            binding.character = character
            binding.executePendingBindings()
        }
    }
}
