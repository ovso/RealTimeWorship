package io.github.ovso.worship.view.ui.bookmark.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.worship.R
import io.github.ovso.worship.app.App
import io.github.ovso.worship.data.mapper.toEntity
import io.github.ovso.worship.data.mapper.toPlayerModel
import io.github.ovso.worship.data.view.BookmarkModel
import io.github.ovso.worship.databinding.ItemBookmarkBinding
import io.github.ovso.worship.view.ui.player.PlayerActivity

class BookmarkViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
  LayoutInflater.from(parent.context).inflate(R.layout.item_bookmark, parent, false)
) {

  private val binding = DataBindingUtil.bind<ItemBookmarkBinding>(itemView)!!
  fun onBindViewHolder(item: BookmarkModel) {
    binding.item = item
    itemView.setOnClickListener { PlayerActivity.start(it.context, item.toPlayerModel()) }
    binding.ivBookmarkDel.setOnClickListener { del(it.context, item) }
  }

  private fun del(context: Context, item: BookmarkModel) {
    Thread {
      (context.applicationContext as? App)?.database?.bookmarkDao()?.delete(item.toEntity())
    }.start()
  }
}
