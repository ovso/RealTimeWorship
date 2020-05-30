package io.github.ovso.worship.view.ui.player

import androidx.databinding.ObservableBoolean
import io.github.ovso.worship.view.base.DisposableViewModel

class PlayerViewModel : DisposableViewModel() {
  val isBookmarkSelected = ObservableBoolean(false)
  var second = 0F

  fun onBookmarkClick() {
    isBookmarkSelected.set(isBookmarkSelected.get().not())
  }
}
