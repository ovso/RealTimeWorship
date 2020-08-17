package io.github.ovso.worship.view.ui.video.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.worship.R
import io.github.ovso.worship.data.toPlayerModel
import io.github.ovso.worship.data.view.VideoModel
import io.github.ovso.worship.databinding.ItemVideoBinding
import io.github.ovso.worship.view.ui.player.PlayerActivity

class VideoViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
  LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
) {

  private val binding = DataBindingUtil.bind<ItemVideoBinding>(itemView)!!

  fun onBindViewHolder(item: VideoModel) {
    binding.item = item
    itemView.setOnClickListener { PlayerActivity.start(it.context, item.toPlayerModel()) }
  }
}
