package io.github.ovso.worship.view.ui.player

import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
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
    val view = View.inflate(context, R.layout.fragment_player, null)
    val container = view.findViewById<LinearLayout>(R.id.ll_player_container)
    val params = container.layoutParams
    params.height = Resources.getSystem().displayMetrics.heightPixels
    container.layoutParams = params
    playerView = view.findViewById(R.id.ypv_player)
    dialog.setContentView(view)
    behavior = BottomSheetBehavior.from(view.parent as View)
    play()
    addEvent()
    return dialog
  }

  private fun addEvent() {
    playerView.addFullScreenListener(object : YouTubePlayerFullScreenListener {
      override fun onYouTubePlayerEnterFullScreen() {
        val layoutParams = playerView.layoutParams
        layoutParams.width = 2000
        playerView.x = -500F
        playerView.layoutParams = layoutParams
        playerView.rotation = 90F
      }

      override fun onYouTubePlayerExitFullScreen() {
        playerView.rotation = 0F
      }
    })
  }

  override fun onStart() {
    super.onStart()
    behavior.state = BottomSheetBehavior.STATE_EXPANDED
  }

  private fun play() {
    arguments?.getString("videoId")?.let {
      playerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
        override fun onReady(youTubePlayer: YouTubePlayer) {
          super.onReady(youTubePlayer)
          youTubePlayer.loadOrCueVideo(lifecycle, it, 0F)
        }

        override fun onCurrentSecond(
          youTubePlayer: YouTubePlayer,
          second: Float
        ) {
          super.onCurrentSecond(youTubePlayer, second)
          //            viewModel.second = second
        }

      })
    }
  }
}
//https://medium.com/@oshanm1/how-to-implement-a-search-dialog-using-full-screen-bottomsheetfragment-29ceb0af3d41
//https://github.com/Oshan94/BottomSheetDaialogFragmentExample
