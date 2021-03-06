package io.github.ovso.worship.app

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.view.ui.bookmark.BookmarkViewModel
import io.github.ovso.worship.view.ui.history.HistoryViewModel
import io.github.ovso.worship.view.ui.player.PlayerViewModel
import io.github.ovso.worship.view.ui.video.VideoViewModel

/**
 * Factory for all ViewModels.
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
  private val repository: TasksRepository,
  private val owner: SavedStateRegistryOwner,
  private val defaultArgs: Bundle? = null,
  private val intent: Intent? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

  override fun <T : ViewModel> create(
    key: String,
    modelClass: Class<T>,
    handle: SavedStateHandle
  ) = with(modelClass) {
    when {
      isAssignableFrom(VideoViewModel::class.java) -> VideoViewModel(repository, defaultArgs)
      isAssignableFrom(PlayerViewModel::class.java) -> PlayerViewModel(
        repository,
        defaultArgs,
        owner
      )
      isAssignableFrom(BookmarkViewModel::class.java) -> BookmarkViewModel(owner, repository)
      isAssignableFrom(HistoryViewModel::class.java) -> HistoryViewModel(owner, repository)
      else ->
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
  } as T
}
