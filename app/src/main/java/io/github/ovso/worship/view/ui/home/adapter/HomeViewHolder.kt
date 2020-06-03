package io.github.ovso.worship.view.ui.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.worship.R
import io.github.ovso.worship.data.view.ChurchModel
import io.github.ovso.worship.databinding.ItemHomeBinding
import io.github.ovso.worship.view.ui.video.VideoActivity

class HomeViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
  LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
) {

  private val binding = DataBindingUtil.bind<ItemHomeBinding>(itemView)!!

  fun onBindViewHolder(item: ChurchModel) {
    binding.item = item
    itemView.setOnClickListener {
      val context = it.context
      val intent = Intent(context, VideoActivity::class.java).apply {
        putExtra("channel_id", item.channelId)
      }
      context.startActivity(intent)
    }
  }
}
