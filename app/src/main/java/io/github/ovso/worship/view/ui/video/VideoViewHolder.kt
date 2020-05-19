package io.github.ovso.worship.view.ui.video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.worship.R
import io.github.ovso.worship.data.view.VideoModel
import io.github.ovso.worship.databinding.ItemVideoBinding

class VideoViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
) {

    private val binding = DataBindingUtil.bind<ItemVideoBinding>(itemView)!!

    fun onBindViewHolder(item: VideoModel) {
        binding.item = item
/*
        binding.ytpvMainItem.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                (itemView.context as? ComponentActivity)?.let {
                    youTubePlayer.loadOrCueVideo(it.lifecycle, item.videoId, 0F)
                    Timber.d("loadOrCueVideo")
                }
            }
        })
*/
    }

}
