package io.github.ovso.worship.view.ui.video.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import io.github.ovso.worship.data.view.VideoModel

class VideoAdapter : ListAdapter<VideoModel, VideoViewHolder>(DIFF_UTIL) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder =
    VideoViewHolder(parent)

  override fun onBindViewHolder(holder: VideoViewHolder, position: Int): Unit =
    holder.onBindViewHolder(getItem(position))
}

val DIFF_UTIL = object : DiffUtil.ItemCallback<VideoModel>() {
  override fun areItemsTheSame(
    oldItem: VideoModel,
    newItem: VideoModel
  ): Boolean = oldItem.videoId == newItem.videoId

  override fun areContentsTheSame(
    oldItem: VideoModel,
    newItem: VideoModel
  ): Boolean = areItemsTheSame(oldItem, newItem)
}
