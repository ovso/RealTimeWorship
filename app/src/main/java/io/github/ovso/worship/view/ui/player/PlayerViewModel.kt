package io.github.ovso.worship.view.ui.player

import android.os.Bundle
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.google.gson.Gson
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.data.view.PlayerModel
import io.github.ovso.worship.view.base.DisposableViewModel

class PlayerViewModel(
  private val repository: TasksRepository,
  arguments: Bundle?
) : DisposableViewModel() {
  private val playerModel: MutableLiveData<PlayerModel> = MutableLiveData()
  val videoId = Transformations.map(playerModel) { model -> model.videoId }
  val title = Transformations.map(playerModel) { model -> model.title }
  val thumbnail = Transformations.map(playerModel) { model -> model.thumbnail }
  val isBookmarkSelected = ObservableBoolean(false)
  var second = 0F

  init {
    arguments?.getString("video_json")?.let {
      Gson().fromJson(it, PlayerModel::class.java)
    }?.let {
      playerModel.value = it
    }
  }

  fun onBookmarkClick() {
    isBookmarkSelected.set(isBookmarkSelected.get().not())
  }
}
