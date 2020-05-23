package io.github.ovso.worship.view.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import io.github.ovso.worship.data.view.HomeModel

class HomeAdapter : ListAdapter<HomeModel, HomeViewHolder>(
  DIFF_UTIL
) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
    HomeViewHolder(parent)

  override fun onBindViewHolder(holder: HomeViewHolder, position: Int): Unit =
    holder.onBindViewHolder(getItem(position))
}

val DIFF_UTIL = object : DiffUtil.ItemCallback<HomeModel>() {
  override fun areItemsTheSame(
    oldItem: HomeModel,
    newItem: HomeModel
  ): Boolean = oldItem.title == newItem.title

  override fun areContentsTheSame(
    oldItem: HomeModel,
    newItem: HomeModel
  ): Boolean = areItemsTheSame(oldItem, newItem)
}
