package io.github.ovso.worship.view.ui.video.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.worship.R
import io.github.ovso.worship.data.toPlayerModel
import io.github.ovso.worship.data.view.VideoModel
import io.github.ovso.worship.databinding.ItemVideoBinding

class VideoViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
  LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
) {

  private val binding = DataBindingUtil.bind<ItemVideoBinding>(itemView)!!

  fun onBindViewHolder(item: VideoModel) {
    binding.item = item
    itemView.setOnClickListener {
      it.findNavController().navigate(
        R.id.playerFragment,
        bundleOf("model" to item.toPlayerModel())
      )
    }
  }
}
