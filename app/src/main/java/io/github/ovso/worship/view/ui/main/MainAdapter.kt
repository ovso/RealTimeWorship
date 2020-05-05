package io.github.ovso.worship.view.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter


class MainAdapter : ListAdapter<MainItem, MainViewHolder>(DIFF_UTIL) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MainViewHolder(parent)

    override fun onBindViewHolder(holder: MainViewHolder, position: Int): Unit =
        holder.onBindViewHolder(getItem(position))
}

val DIFF_UTIL = object : DiffUtil.ItemCallback<MainItem>() {
    override fun areItemsTheSame(
        oldItem: MainItem,
        newItem: MainItem
    ): Boolean = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: MainItem,
        newItem: MainItem
    ): Boolean = areItemsTheSame(oldItem, newItem)
}
