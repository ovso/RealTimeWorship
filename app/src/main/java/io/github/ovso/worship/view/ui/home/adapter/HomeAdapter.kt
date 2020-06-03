package io.github.ovso.worship.view.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import io.github.ovso.worship.data.view.ChurchModel

class HomeAdapter : ListAdapter<ChurchModel, HomeViewHolder>(
  DIFF_UTIL
) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
    HomeViewHolder(parent)

  override fun onBindViewHolder(holder: HomeViewHolder, position: Int): Unit =
    holder.onBindViewHolder(getItem(position))
}

val DIFF_UTIL = object : DiffUtil.ItemCallback<ChurchModel>() {
  override fun areItemsTheSame(
      oldItem: ChurchModel,
      newItem: ChurchModel
  ): Boolean = oldItem.title == newItem.title

  override fun areContentsTheSame(
      oldItem: ChurchModel,
      newItem: ChurchModel
  ): Boolean = areItemsTheSame(oldItem, newItem)
}
