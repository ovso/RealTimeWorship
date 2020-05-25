package io.github.ovso.worship.view.ui.video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.worship.R
import io.github.ovso.worship.data.view.VideoModel
import io.github.ovso.worship.databinding.ItemVideoBinding
import io.github.ovso.worship.view.ui.player.PlayerFragment

class VideoViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
  LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
) {

  private val binding = DataBindingUtil.bind<ItemVideoBinding>(itemView)!!

  fun onBindViewHolder(item: VideoModel) {
    binding.item = item
    itemView.setOnClickListener {
      (itemView.context as? FragmentActivity)?.supportFragmentManager?.let { fm ->
        PlayerFragment.newInstance(item.videoId).show(
          fm,
          PlayerFragment::class.java.name
        )
      }

    }
  }
}
