package io.github.ovso.worship.view.ui.bookmark.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import io.github.ovso.worship.data.view.BookmarkModel

class BookmarkAdapter : ListAdapter<BookmarkModel, BookmarkViewHolder>(
  DIFF_UTIL
) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder =
    BookmarkViewHolder(parent)

  override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int): Unit =
    holder.onBindViewHolder(getItem(position))
}

val DIFF_UTIL = object : DiffUtil.ItemCallback<BookmarkModel>() {
  override fun areItemsTheSame(
    oldItem: BookmarkModel,
    newItem: BookmarkModel
  ): Boolean = oldItem.videoId == newItem.videoId

  override fun areContentsTheSame(
    oldItem: BookmarkModel,
    newItem: BookmarkModel
  ): Boolean = areItemsTheSame(oldItem, newItem)
}
