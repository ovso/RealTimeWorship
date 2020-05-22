package io.github.ovso.worship.view.ui.player

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import io.github.ovso.worship.R
import io.github.ovso.worship.databinding.FragmentPlayerBinding
import io.github.ovso.worship.extensions.getViewModelFactory
import io.github.ovso.worship.view.base.DataBindingFragment
import timber.log.Timber

class PlayerFragment : DataBindingFragment<FragmentPlayerBinding>(R.layout.fragment_player) {

  override val viewModel by viewModels<PlayerViewModel> { getViewModelFactory() }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setHasOptionsMenu(true)
    play()
  }

  private fun play() {
    requireActivity().intent.getStringExtra("videoId")?.let {
      binding.ypvPlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
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

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    requireActivity().finish()
    return super.onOptionsItemSelected(item)
  }
}
