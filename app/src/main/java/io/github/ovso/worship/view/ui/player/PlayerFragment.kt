package io.github.ovso.worship.view.ui.player

import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import io.github.ovso.worship.R
import io.github.ovso.worship.data.view.PlayerModel
import io.github.ovso.worship.databinding.ViewPlayerBinding
import io.github.ovso.worship.extensions.getIndicatorSize
import io.github.ovso.worship.extensions.getViewModelFactory
import timber.log.Timber


class PlayerFragment private constructor() : BottomSheetDialogFragment() {
  private val viewModel by viewModels<PlayerViewModel> { getViewModelFactory() }

  companion object {
    fun newInstance(videoJson: String): PlayerFragment =
      PlayerFragment().apply {
        arguments = bundleOf("video_json" to videoJson)
      }
  }

  private lateinit var behavior: BottomSheetBehavior<View>

  private lateinit var binding: ViewPlayerBinding
  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    Timber.d("Player onCreateDialog")
    binding = DataBindingUtil.bind(View.inflate(context, R.layout.view_player, null))!!
    binding.viewModel = viewModel
    val playerContainerParams = binding.llPlayerContainer.layoutParams
    playerContainerParams.height = Resources.getSystem().displayMetrics.heightPixels
    binding.llPlayerContainer.layoutParams = playerContainerParams
    binding.ypvPlayer.addFullScreenListener(fullScreenListener)
    lifecycle.addObserver(binding.ypvPlayer)
    val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
    dialog.setContentView(binding.root)
    behavior = BottomSheetBehavior.from(binding.root.parent as View)
//    playVideo()
    return dialog
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
    }

    override fun onYouTubePlayerExitFullScreen() {
      switchToPortraitMode()
      binding.ivPlayerBookmark.isVisible = true
    }
  }

  private fun getScreenSize(): Point {
    val display = requireActivity().windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size
  }

  override fun onStart() {
    super.onStart()
    Timber.d("Player onStart")
    behavior.state = BottomSheetBehavior.STATE_EXPANDED
  }

  private fun playVideo(videoId: String) {
    binding.ypvPlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
      override fun onReady(youTubePlayer: YouTubePlayer) {
        super.onReady(youTubePlayer)
        youTubePlayer.loadOrCueVideo(lifecycle, videoId, 0F)
      }
    })
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    Timber.d("Player onAttach")
    var playerModel = arguments?.getString("video_json")?.let {
      Gson().fromJson(it, PlayerModel::class.java)
    }
  }

  override fun onResume() {
    super.onResume()
    Timber.d("Player onResume")
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    Timber.d("Player onCreateView")
    observe()
    return super.onCreateView(inflater, container, savedInstanceState)
  }

  private fun observe() {
    viewModel.videoId.observeForever {
      playVideo(it)
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    Timber.d("Player onDestroy")
  }

  override fun onDetach() {
    super.onDetach()
    Timber.d("Player onDetach")
  }
}
//https://medium.com/@oshanm1/how-to-implement-a-search-dialog-using-full-screen-bottomsheetfragment-29ceb0af3d41
//https://github.com/Oshan94/BottomSheetDaialogFragmentExample
