package io.github.ovso.worship.view.ui.player

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import io.github.ovso.worship.R
import io.github.ovso.worship.data.view.PlayerModel
import io.github.ovso.worship.databinding.ActivityPlayerBinding
import io.github.ovso.worship.extensions.getIndicatorSize
import io.github.ovso.worship.extensions.getScreenSize
import io.github.ovso.worship.extensions.getViewModelFactory
import io.github.ovso.worship.view.base.DataBindingActivity
import kotlinx.android.synthetic.main.activity_player.*
import timber.log.Timber

class PlayerActivity : DataBindingActivity<ActivityPlayerBinding>(R.layout.activity_player) {

  private val viewModel by viewModels<PlayerViewModel> { getViewModelFactory() }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    supportActionBar?.hide()
    observe()
//    play()
  }

  private fun observe() {
    viewModel.videoId.observe(this, Observer {
      play(it)
    })
    binding.ypvPlayer.addFullScreenListener(fullScreenListener)
  }

  private val fullScreenListener = object : YouTubePlayerFullScreenListener {
    override fun onYouTubePlayerEnterFullScreen() {
      switchToLandscapeMode()
      binding.ivPlayerBookmark.isVisible = false
      binding.tvPlayerTitle.isVisible = false
    }

    override fun onYouTubePlayerExitFullScreen() {
      switchToPortraitMode()
      binding.ivPlayerBookmark.isVisible = true
      binding.tvPlayerTitle.isVisible = true
    }
  }

  private fun switchToPortraitMode() {
    binding.ypvPlayer.rotation = 0F
    binding.ypvPlayer.x = 0F
  }

  private fun switchToLandscapeMode() {
    val params = binding.ypvPlayer.layoutParams
    val screenSize = getScreenSize()
    val screenSizeX = screenSize.x
    val screenSizeY = screenSize.y
    params.height = screenSizeX
    params.width = screenSizeY - getIndicatorSize()
    with(binding.ypvPlayer) {
      layoutParams = params
      pivotX = (screenSizeX / 2).toFloat()
      pivotY = (screenSizeX / 2).toFloat()
      rotation = 90F
    }
  }


  private fun play(videoId: String) {
    ypv_player.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
      override fun onReady(youTubePlayer: YouTubePlayer) {
        super.onReady(youTubePlayer)
        youTubePlayer.loadOrCueVideo(lifecycle, videoId, viewModel.second)
        Timber.d("loadOrCueVideo")
      }
    })
  }

  companion object {
    fun start(context: Context, playerModel: PlayerModel) {
      context.startActivity(
        Intent(context, PlayerActivity::class.java).apply {
          putExtra("model", playerModel)
        }
      )
    }
  }
}
