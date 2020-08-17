package io.github.ovso.worship.view.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import io.github.ovso.worship.data.view.HomeItemModel

class HomeAdapter(
  private var onItemClickListener: ((HomeItemModel) -> Unit)? = null
) : ListAdapter<HomeItemModel, HomeViewHolder>(
  DIFF_UTIL
) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
    HomeViewHolder(parent, onItemClickListener)

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
