package com.kimym.rickandmorty.binding

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.facebook.shimmer.ShimmerFrameLayout
import com.kimym.rickandmorty.base.BaseRickAndMortyPagingAdapter
import com.kimym.rickandmorty.base.BaseRickAndMortyPagingAdapter.Companion.withLoadStateAdapter

@BindingAdapter("isVisible")
fun View.isVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("startShimmer")
fun ShimmerFrameLayout.startShimmer(isLoading: Boolean) {
    if (isLoading) startShimmer() else stopShimmer()
}

@BindingAdapter("setImage")
fun ImageView.setImage(url: String?) {
    url?.let {
        Glide.with(this)
            .load(url)
            .into(this)
    }
}

@BindingAdapter("setStatusColor")
fun ImageView.setStatusColor(status: String?) {
    imageTintList = ColorStateList.valueOf(
        when (status) {
            "Alive" -> Color.GREEN
            "Dead" -> Color.RED
            else -> Color.GRAY
        }
    )
}

@BindingAdapter("setPagingWithLoadStateAdapter")
fun RecyclerView.setPagingWithLoadStateAdapter(rickAndMortyAdapter: BaseRickAndMortyPagingAdapter) {
    adapter = rickAndMortyAdapter.withLoadStateAdapter()
}
