package io.github.ovso.worship.view.ui.player

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import io.github.ovso.worship.R
import io.github.ovso.worship.extensions.getViewModelFactory
import kotlinx.android.synthetic.main.view_player.*
import timber.log.Timber

class PlayerActivity : AppCompatActivity(R.layout.activity_player) {

  private val viewModel by viewModels<PlayerViewModel> { getViewModelFactory() }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    play()
  }

  private fun play() {
    intent.getStringExtra("videoId")?.let {
      ypv_player.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
        override fun onReady(youTubePlayer: YouTubePlayer) {
          super.onReady(youTubePlayer)
          youTubePlayer.loadOrCueVideo(lifecycle, it, viewModel.second)
          Timber.d("loadOrCueVideo")
        }

        override fun onCurrentSecond(
          youTubePlayer: YouTubePlayer,
          second: Float
        ) {
          super.onCurrentSecond(youTubePlayer, second)
          viewModel.second = second
        }
      })
    }
  }

}
