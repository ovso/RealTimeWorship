package io.github.ovso.worship.view.ui.player

import android.content.Intent
import android.os.Bundle
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.data.view.PlayerModel
import io.github.ovso.worship.view.base.DisposableViewModel

class PlayerViewModel(
  private val repository: TasksRepository,
  arguments: Bundle?,
  intent: Intent? = null
) : DisposableViewModel() {
  private val playerModel: MutableLiveData<PlayerModel> = MutableLiveData()

  //  val title = Transformations.map(playerModel) { model -> model.title }
  val title = ObservableField<String>()
  val videoId = Transformations.map(playerModel) { model -> model.videoId }

  //  val title = Transformations.map(playerModel) { model -> model.title }
  val thumbnail = Transformations.map(playerModel) { model -> model.thumbnail }

  val isBookmarkSelected = ObservableBoolean(false)

  var second = 0F

  init {
    intent?.getParcelableExtra<PlayerModel>("model")?.let {
      playerModel.value = it
      title.set(it.title)
    }
  }

  fun onBookmarkClick() {
    isBookmarkSelected.set(isBookmarkSelected.get().not())
  }
}
