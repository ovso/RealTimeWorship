package io.github.ovso.worship.view.ui.player

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import io.github.ovso.worship.R
import io.github.ovso.worship.databinding.ActivityPlayerBinding
import io.github.ovso.worship.extensions.getViewModelFactory
import io.github.ovso.worship.view.base.DataBindingActivity
import kotlinx.android.synthetic.main.view_player.*
import timber.log.Timber

class PlayerActivity : DataBindingActivity<ActivityPlayerBinding>(R.layout.activity_player) {

//  private val viewModel by viewModels<PlayerViewModel> { getViewModelFactory() }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    play()
  }

  private fun play() {
/*
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
*/
  }

  override val viewModel: ViewModel
    get() = ViewModelProvider(
      this@PlayerActivity,
      getViewModelFactory()
    )[PlayerViewModel::class.java]

}
