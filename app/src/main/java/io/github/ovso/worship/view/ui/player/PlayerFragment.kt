package io.github.ovso.worship.view.ui.player

import android.app.Dialog
import android.content.res.Resources
import android.graphics.Point
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import io.github.ovso.worship.R


class PlayerFragment private constructor() : BottomSheetDialogFragment() {
  private lateinit var playerView: YouTubePlayerView

  companion object {
    fun newInstance(videoId: String): PlayerFragment =
      PlayerFragment().apply {
        arguments = bundleOf("videoId" to videoId)
      }
  }

  private lateinit var behavior: BottomSheetBehavior<View>

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
    val view = View.inflate(context, R.layout.view_player, null)
    val container = view.findViewById<LinearLayout>(R.id.ll_player_container)
    val params = container.layoutParams
    params.height = Resources.getSystem().displayMetrics.heightPixels
    container.layoutParams = params
    playerView = view.findViewById(R.id.ypv_player)
    playerView.addFullScreenListener(fullScreenListener)
    lifecycle.addObserver(playerView)
    dialog.setContentView(view)
    behavior = BottomSheetBehavior.from(view.parent as View)
    playVideo()
    return dialog
  }

  private fun switchToLandscapeMode() {
    val params = playerView.layoutParams
    val screenSizeX = getScreenSize().x
    val screenSizeY = getScreenSize().y
    params.height = screenSizeX
    params.width = screenSizeY - getIndicatorSize()
    playerView.layoutParams = params
    playerView.pivotX = (screenSizeX / 2).toFloat()
    playerView.pivotY = (screenSizeX / 2).toFloat()
    playerView.rotation = 90F
  }

  private fun getIndicatorSize(): Int {
    var result = 0

    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")

    if (resourceId > 0) {
      result = resources.getDimensionPixelSize(resourceId)
    }
    return result
  }

  private fun switchToPortraitMode() {
    playerView.rotation = 0F
    playerView.x = 0F
  }

  private val fullScreenListener = object : YouTubePlayerFullScreenListener {
    override fun onYouTubePlayerEnterFullScreen() {
      switchToLandscapeMode()
    }

    override fun onYouTubePlayerExitFullScreen() {
      switchToPortraitMode()
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

  override fun onDestroyView() {
    playerView.removeFullScreenListener(fullScreenListener)
    super.onDestroyView()
  }
}
//https://medium.com/@oshanm1/how-to-implement-a-search-dialog-using-full-screen-bottomsheetfragment-29ceb0af3d41
//https://github.com/Oshan94/BottomSheetDaialogFragmentExample
