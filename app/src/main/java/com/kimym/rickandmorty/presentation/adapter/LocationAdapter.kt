package com.kimym.rickandmorty.presentation.adapter

import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import com.kimym.rickandmorty.BR
import com.kimym.rickandmorty.R
import com.kimym.rickandmorty.base.BaseRickAndMortyPagingAdapter
import com.kimym.rickandmorty.data.entity.SealedRickAndMortyEntity
import com.kimym.rickandmorty.presentation.location.LocationFragmentDirections

class LocationAdapter : BaseRickAndMortyPagingAdapter() {
    override fun getViewType(): Int {
        return R.layout.item_location
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        getItem(position)?.let {
            val location = it as SealedRickAndMortyEntity.LocationEntity
            binding.setVariable(BR.location, location.entity)
            binding.root.setOnClickListener { view ->
                val directions = LocationFragmentDirections
                val action = directions.actionLocationFragmentToLocationDetail(location.entity)
                view.findNavController().navigate(action)
            }
        }
    }
}
