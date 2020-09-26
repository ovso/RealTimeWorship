package io.github.ovso.worship.view.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import io.github.ovso.worship.data.view.HomeItemModel
import javax.inject.Inject

class HomeAdapter @Inject constructor() : ListAdapter<HomeItemModel, HomeViewHolder>(
  DIFF_UTIL
) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
    HomeViewHolder(parent)

  override fun onBindViewHolder(holder: HomeViewHolder, position: Int): Unit =
    holder.onBindViewHolder(getItem(position))
}

val DIFF_UTIL = object : DiffUtil.ItemCallback<HomeItemModel>() {
  override fun areItemsTheSame(
    oldItem: HomeItemModel,
    newItem: HomeItemModel
  ): Boolean = oldItem.title == newItem.title

  override fun areContentsTheSame(
    oldItem: HomeItemModel,
    newItem: HomeItemModel
  ): Boolean = areItemsTheSame(oldItem, newItem)
}
