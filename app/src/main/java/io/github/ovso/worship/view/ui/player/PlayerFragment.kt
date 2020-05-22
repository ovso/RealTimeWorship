package io.github.ovso.worship.view.ui.player

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import io.github.ovso.worship.R
import io.github.ovso.worship.databinding.FragmentPlayerBinding
import io.github.ovso.worship.extensions.getViewModelFactory
import io.github.ovso.worship.view.base.DataBindingFragment
import kotlinx.android.synthetic.main.fragment_player.*

class PlayerFragment : DataBindingFragment<FragmentPlayerBinding>(R.layout.fragment_player) {

  override val viewModel by viewModels<PlayerViewModel> { getViewModelFactory() }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    observe()
    val videoId = requireActivity().intent.getStringExtra("videoId")
    videoId?.let {
/*
      ypv_player.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
        override fun onReady(youTubePlayer: YouTubePlayer) {
          super.onReady(youTubePlayer)
          youTubePlayer.cueVideo(videoId, 0F)
//        youTubePlayer.loadOrCueVideo(lifecycle, it, binding.viewModel!!.second)
//        Timber.d("loadOrCueVideo")
        }

        override fun onCurrentSecond(
          youTubePlayer: YouTubePlayer,
          second: Float
        ) {
          super.onCurrentSecond(youTubePlayer, second)
//        binding.viewModel?.second = second
        }
      })
*/

    }
  }

  private fun observe() {

  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
  }
}
