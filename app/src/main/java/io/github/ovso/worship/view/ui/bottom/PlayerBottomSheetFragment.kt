package io.github.ovso.worship.view.ui.bottom

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
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import io.github.ovso.worship.R
import timber.log.Timber


class PlayerBottomSheetFragment private constructor() : BottomSheetDialogFragment() {

  companion object {
    fun newInstance(videoId: String): PlayerBottomSheetFragment =
      PlayerBottomSheetFragment().apply {
        arguments = bundleOf("videoId" to videoId)
      }
  }

  lateinit var behavior: BottomSheetBehavior<View>

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
    val view = View.inflate(context, R.layout.fragment_player, null)
    val container = view.findViewById<LinearLayout>(R.id.ll_player_container)
    val params = container.layoutParams
    params.height = Resources.getSystem().displayMetrics.heightPixels
    container.layoutParams = params
    play(view)
    dialog.setContentView(view)
    behavior = BottomSheetBehavior.from(view.parent as View)
    return dialog
  }

  override fun onStart() {
    super.onStart()
    behavior.state = BottomSheetBehavior.STATE_EXPANDED
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    play(view)
  }

  private fun play(view: View) {
    arguments?.getString("videoId")?.let {
      view.findViewById<YouTubePlayerView>(R.id.ypv_player)
        .addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
          override fun onReady(youTubePlayer: YouTubePlayer) {
            super.onReady(youTubePlayer)
  //            youTubePlayer.loadOrCueVideo(lifecycle, it, viewModel.second)
            youTubePlayer.loadOrCueVideo(lifecycle, it, 0F)
            Timber.d("loadOrCueVideo")
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
