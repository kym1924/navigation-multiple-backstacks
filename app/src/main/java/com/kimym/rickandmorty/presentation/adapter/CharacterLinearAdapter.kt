package com.kimym.rickandmorty.presentation.adapter

import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import com.kimym.rickandmorty.BR
import com.kimym.rickandmorty.R
import com.kimym.rickandmorty.base.BaseRickAndMortyPagingAdapter
import com.kimym.rickandmorty.data.entity.SealedRickAndMortyEntity
import com.kimym.rickandmorty.presentation.character.CharacterFragmentDirections

class CharacterLinearAdapter : BaseRickAndMortyPagingAdapter() {
    override fun getViewType(): Int {
        return R.layout.item_character
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        getItem(position)?.let {
            val character = it as SealedRickAndMortyEntity.CharacterEntity
            binding.setVariable(BR.character, character.entity)
            binding.root.setOnClickListener { view ->
                val directions = CharacterFragmentDirections
                val action = directions.actionCharacterToCharacterDetail(character.entity)
                view.findNavController().navigate(action)
            }
        }
    }
}
