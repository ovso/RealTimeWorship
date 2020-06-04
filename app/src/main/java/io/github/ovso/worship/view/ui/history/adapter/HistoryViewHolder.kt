package io.github.ovso.worship.view.ui.history.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.worship.R
import io.github.ovso.worship.app.App
import io.github.ovso.worship.data.mapper.toEntity
import io.github.ovso.worship.data.mapper.toPlayerModel
import io.github.ovso.worship.data.view.HistoryModel
import io.github.ovso.worship.databinding.ItemBookmarkBinding
import io.github.ovso.worship.databinding.ItemHistoryBinding
import io.github.ovso.worship.view.ui.player.PlayerActivity

class HistoryViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
  LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
) {

  private val binding = DataBindingUtil.bind<ItemHistoryBinding>(itemView)!!
  fun onBindViewHolder(item: HistoryModel) {
    binding.item = item
    itemView.setOnClickListener { PlayerActivity.start(it.context, item.toPlayerModel()) }
    binding.ivHistoryItemDel.setOnClickListener { del(it.context, item) }
  }

  private fun del(context: Context, item: HistoryModel) {
    Thread {
      (context.applicationContext as? App)?.database?.historyDao()?.delete(item.toEntity())
    }.start()
  }
}
