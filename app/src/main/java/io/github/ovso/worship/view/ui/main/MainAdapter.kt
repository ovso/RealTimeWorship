package io.github.ovso.worship.view.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import io.github.ovso.worship.data.view.VideoModel


class MainAdapter : ListAdapter<VideoModel, MainViewHolder>(DIFF_UTIL) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(parent)

    override fun onBindViewHolder(holder: MainViewHolder, position: Int): Unit =
        holder.onBindViewHolder(getItem(position))
}

val DIFF_UTIL = object : DiffUtil.ItemCallback<VideoModel>() {
    override fun areItemsTheSame(
        oldItem: VideoModel,
        newItem: VideoModel
    ): Boolean = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: VideoModel,
        newItem: VideoModel
    ): Boolean = areItemsTheSame(oldItem, newItem)
}
