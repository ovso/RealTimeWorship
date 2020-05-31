package io.github.ovso.worship.view.ui.player

import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import io.github.ovso.worship.R
import io.github.ovso.worship.data.view.PlayerModel
import io.github.ovso.worship.databinding.ViewPlayerBinding
import io.github.ovso.worship.extensions.getIndicatorSize
import io.github.ovso.worship.extensions.getViewModelFactory
import timber.log.Timber


class PlayerFragment private constructor() : BottomSheetDialogFragment() {
  private lateinit var playerView: YouTubePlayerView
  private val viewModel by viewModels<PlayerViewModel> { getViewModelFactory() }

  companion object {
    fun newInstance(videoJson: String): PlayerFragment =
      PlayerFragment().apply {
        arguments = bundleOf("video_json" to videoJson)
      }
  }

  private lateinit var behavior: BottomSheetBehavior<View>
  private lateinit var contentView: View

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    Timber.d("Player onCreateDialog")
    val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
    contentView = View.inflate(context, R.layout.view_player, null)
    val container = contentView.findViewById<LinearLayout>(R.id.ll_player_container)
    val params = container.layoutParams
    params.height = Resources.getSystem().displayMetrics.heightPixels
    container.layoutParams = params
    playerView = contentView.findViewById(R.id.ypv_player)
    playerView.addFullScreenListener(fullScreenListener)
    lifecycle.addObserver(playerView)
    dialog.setContentView(contentView)
    dataBinding(contentView)
    behavior = BottomSheetBehavior.from(contentView.parent as View)
    playVideo()
    return dialog
  }

  private fun dataBinding(view: View) {
    val bind = DataBindingUtil.bind<ViewPlayerBinding>(view)
    bind?.viewModel = viewModel
//    bind?.lifecycleOwner = viewLifecycleOwner
  }

  private fun switchToLandscapeMode() {
    val params = playerView.layoutParams
    val screenSizeX = getScreenSize().x
    val screenSizeY = getScreenSize().y
    params.height = screenSizeX
    params.width = screenSizeY - requireContext().getIndicatorSize()
    playerView.layoutParams = params
    playerView.pivotX = (screenSizeX / 2).toFloat()
    playerView.pivotY = (screenSizeX / 2).toFloat()
    playerView.rotation = 90F
  }

  private fun switchToPortraitMode() {
    playerView.rotation = 0F
    playerView.x = 0F
  }

  private val fullScreenListener = object : YouTubePlayerFullScreenListener {
    override fun onYouTubePlayerEnterFullScreen() {
      switchToLandscapeMode()
      contentView.findViewById<ImageView>(R.id.iv_player_bookmark).isVisible = false
    }

    override fun onYouTubePlayerExitFullScreen() {
      switchToPortraitMode()
      contentView.findViewById<ImageView>(R.id.iv_player_bookmark).isVisible = true
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

  private fun playVideo() {
    arguments?.getString("videoId")?.let { videoId ->
      playerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
        override fun onReady(youTubePlayer: YouTubePlayer) {
          super.onReady(youTubePlayer)
          youTubePlayer.loadOrCueVideo(lifecycle, videoId, 0F)
        }

      })
    }
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    var playerModel = arguments?.getString("video_json")?.let {
      Gson().fromJson(it, PlayerModel::class.java)
    }
    Timber.d("Player onAttach")
  }

  override fun onResume() {
    super.onResume()
    Timber.d("Player onResume")
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    Timber.d("Player onActivityCreated deprecated")
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    Timber.d("Player onCreateView")
    return super.onCreateView(inflater, container, savedInstanceState)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Timber.d("Player onViewCreated")
  }

  override fun onDestroy() {
    super.onDestroy()
    Timber.d("Player onDestroy")
  }

  override fun onDetach() {
    super.onDetach()
    playerView.removeFullScreenListener(fullScreenListener)
    Timber.d("Player onDetach")
  }
}
//https://medium.com/@oshanm1/how-to-implement-a-search-dialog-using-full-screen-bottomsheetfragment-29ceb0af3d41
//https://github.com/Oshan94/BottomSheetDaialogFragmentExample
