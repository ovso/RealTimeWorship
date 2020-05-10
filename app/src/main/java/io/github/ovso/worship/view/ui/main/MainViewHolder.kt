package io.github.ovso.worship.view.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import io.github.ovso.worship.R
import io.github.ovso.worship.data.view.VideoModel
import io.github.ovso.worship.databinding.ItemMainBinding
import timber.log.Timber

class MainViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
) {

    private val binding = DataBindingUtil.bind<ItemMainBinding>(itemView)!!

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
