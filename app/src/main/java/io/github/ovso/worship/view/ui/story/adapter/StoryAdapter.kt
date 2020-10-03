package io.github.ovso.worship.view.ui.story.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import io.github.ovso.worship.data.view.StoryItemModel
import javax.inject.Inject

class StoryAdapter @Inject constructor() : ListAdapter<StoryItemModel, StoryViewHolder>(
  DIFF_UTIL
) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder =
    StoryViewHolder.create(parent)

  override fun onBindViewHolder(holder: StoryViewHolder, position: Int): Unit =
    holder.onBindViewHolder(getItem(position))
}

val DIFF_UTIL = object : DiffUtil.ItemCallback<StoryItemModel>() {
  override fun areItemsTheSame(
    oldItem: StoryItemModel,
    newItem: StoryItemModel
  ): Boolean = oldItem.title == newItem.title

  override fun areContentsTheSame(
    oldItem: StoryItemModel,
    newItem: StoryItemModel
  ): Boolean = areItemsTheSame(oldItem, newItem)
}
