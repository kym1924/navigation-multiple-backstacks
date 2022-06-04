package com.kimym.rickandmorty.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ConcatAdapter
import com.kimym.rickandmorty.data.entity.SealedRickAndMortyEntity
import com.kimym.rickandmorty.presentation.adapter.RickAndMortyLoadStateAdapter

abstract class BaseRickAndMortyPagingAdapter :
    PagingDataAdapter<SealedRickAndMortyEntity, BaseViewHolder>(
        BaseDiffUtilItemCallback(
            itemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
            contentsTheSame = { oldItem, newItem -> oldItem == newItem }
        )
    ) {

    override fun getItemViewType(position: Int): Int {
        return getViewType()
    }

    abstract fun getViewType(): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, viewType, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        bind(holder.binding, position)
        holder.binding.executePendingBindings()
    }

    abstract fun bind(binding: ViewDataBinding, position: Int)

    companion object {
        fun BaseRickAndMortyPagingAdapter.withLoadStateAdapter(): ConcatAdapter {
            return withLoadStateHeaderAndFooter(
                header = RickAndMortyLoadStateAdapter { retry() },
                footer = RickAndMortyLoadStateAdapter { retry() }
            )
        }
    }
}
