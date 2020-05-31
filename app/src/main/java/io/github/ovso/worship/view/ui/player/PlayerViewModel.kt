package io.github.ovso.worship.view.ui.player

import android.os.Bundle
import androidx.databinding.ObservableBoolean
import com.google.gson.Gson
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.data.view.PlayerModel
import io.github.ovso.worship.view.base.DisposableViewModel
import timber.log.Timber

class PlayerViewModel(
  private val repository: TasksRepository,
  arguments: Bundle?
) : DisposableViewModel() {
  private var playerModel: PlayerModel? = arguments?.getString("video_json")?.let {
    Gson().fromJson(it, PlayerModel::class.java)
  }

  init {
      Timber.d(playerModel.toString())
  }
  val isBookmarkSelected = ObservableBoolean(false)
  var second = 0F
  fun onBookmarkClick() {
    isBookmarkSelected.set(isBookmarkSelected.get().not())
  }
}
