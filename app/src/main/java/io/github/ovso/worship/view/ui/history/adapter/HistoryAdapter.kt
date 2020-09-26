package io.github.ovso.worship.view.ui.history.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import io.github.ovso.worship.data.view.HistoryModel
import javax.inject.Inject

class HistoryAdapter @Inject constructor() : ListAdapter<HistoryModel, HistoryViewHolder>(
  DIFF_UTIL
) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder =
    HistoryViewHolder(parent)

  override fun onBindViewHolder(holder: HistoryViewHolder, position: Int): Unit =
    holder.onBindViewHolder(getItem(position))
}

val DIFF_UTIL = object : DiffUtil.ItemCallback<HistoryModel>() {
  override fun areItemsTheSame(
    oldItem: HistoryModel,
    newItem: HistoryModel
  ): Boolean = oldItem.videoId == newItem.videoId

  override fun areContentsTheSame(
    oldItem: HistoryModel,
    newItem: HistoryModel
  ): Boolean = areItemsTheSame(oldItem, newItem)
}
