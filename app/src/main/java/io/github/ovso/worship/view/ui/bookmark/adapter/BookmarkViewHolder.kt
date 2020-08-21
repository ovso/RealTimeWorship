package io.github.ovso.worship.view.ui.bookmark.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.worship.R
import io.github.ovso.worship.app.App
import io.github.ovso.worship.data.toEntity
import io.github.ovso.worship.data.toPlayerModel
import io.github.ovso.worship.data.view.BookmarkModel
import io.github.ovso.worship.databinding.ItemBookmarkBinding

class BookmarkViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
  LayoutInflater.from(parent.context).inflate(R.layout.item_bookmark, parent, false)
) {

  private val binding = DataBindingUtil.bind<ItemBookmarkBinding>(itemView)!!
  fun onBindViewHolder(item: BookmarkModel) {
    binding.item = item
    itemView.setOnClickListener {
      it.findNavController().navigate(
        R.id.playerFragment,
        bundleOf("model" to item.toPlayerModel())
      )
    }
    binding.ivBookmarkDel.setOnClickListener { del(it.context, item) }
  }

  private fun del(context: Context, item: BookmarkModel) {
    Thread {
      (context.applicationContext as? App)?.database?.bookmarkDao()?.delete(item.toEntity())
    }.start()
  }
}
