package io.github.ovso.worship.view.ui.history.adapter

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
import io.github.ovso.worship.data.view.HistoryModel
import io.github.ovso.worship.databinding.ItemHistoryBinding

class HistoryViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
  LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
) {

  private val binding = DataBindingUtil.bind<ItemHistoryBinding>(itemView)!!

  fun onBindViewHolder(item: HistoryModel) {
    binding.item = item
    itemView.setOnClickListener {
      it.findNavController().navigate(
        R.id.playerFragment,
        bundleOf("model" to item.toPlayerModel())
      )
    }
    binding.ivHistoryItemDel.setOnClickListener { del(it.context, item) }
  }

  private fun del(context: Context, item: HistoryModel) {
    Thread {
      (context.applicationContext as? App)?.database?.historyDao()?.delete(item.toEntity())
    }.start()
  }
}
