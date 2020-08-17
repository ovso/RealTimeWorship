package io.github.ovso.worship.view.ui.player

import android.graphics.Point
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import io.github.ovso.worship.R
import io.github.ovso.worship.databinding.FragmentPlayerBinding
import io.github.ovso.worship.extensions.getIndicatorSize
import io.github.ovso.worship.extensions.getViewModelFactory
import io.github.ovso.worship.view.base.DataBindingFragment


class PlayerFragment : DataBindingFragment<FragmentPlayerBinding>(R.layout.fragment_player) {

  override val viewModel by viewModels<PlayerViewModel> { getViewModelFactory() }

  companion object {
    fun newInstance(videoJson: String): PlayerFragment =
      PlayerFragment().apply {
        arguments = bundleOf("video_json" to videoJson)
      }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupPlayer()
    observe()
  }

  private fun setupPlayer() {
    lifecycle.addObserver(binding.ypvPlayer)
    binding.ypvPlayer.addFullScreenListener(fullScreenListener)
  }

  private fun switchToLandscapeMode() {
    val params = binding.ypvPlayer.layoutParams
    val screenSizeX = getScreenSize().x
    val screenSizeY = getScreenSize().y
    params.height = screenSizeX
    params.width = screenSizeY - requireContext().getIndicatorSize()
    with(binding.ypvPlayer) {
      layoutParams = params
      pivotX = (screenSizeX / 2).toFloat()
      pivotY = (screenSizeX / 2).toFloat()
      rotation = 90F
    }
  }

  private fun switchToPortraitMode() {
    binding.ypvPlayer.rotation = 0F
    binding.ypvPlayer.x = 0F
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

  private fun getScreenSize(): Point {
    val display = requireActivity().windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size
  }

  private fun playVideo(videoId: String) {
    binding.ypvPlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
      override fun onReady(youTubePlayer: YouTubePlayer) {
        super.onReady(youTubePlayer)
        youTubePlayer.loadOrCueVideo(lifecycle, videoId, 0F)
      }
    })
  }

  private fun observe() {
    viewModel.videoId.observe(viewLifecycleOwner, Observer {
      playVideo(it)
    })
  }

}
//https://medium.com/@oshanm1/how-to-implement-a-search-dialog-using-full-screen-bottomsheetfragment-29ceb0af3d41
//https://github.com/Oshan94/BottomSheetDaialogFragmentExample
