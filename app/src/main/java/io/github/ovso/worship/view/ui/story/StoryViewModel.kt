package io.github.ovso.worship.view.ui.story

import androidx.lifecycle.viewModelScope
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import io.github.ovso.worship.app.App
import io.github.ovso.worship.data.Repository
import io.github.ovso.worship.data.toStoryModels
import io.github.ovso.worship.data.view.StoryItemModel
import io.github.ovso.worship.view.base.mvrx.MvRxViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

data class StoryState(
  val items: List<StoryItemModel> = emptyList()
) : MvRxState

class StoryViewModel(
  private val initialState: StoryState,
  private val repository: Repository
) : MvRxViewModel<StoryState>(initialState) {

  private var job: Job? = null

  init {
    reqStories()
  }

  private fun reqStories() {
    val handlerException = CoroutineExceptionHandler { _, throwable ->
      Timber.e(throwable)
    }
    job = viewModelScope.launch(handlerException) {
      val items = repository.stories().toStoryModels()
      setState {
        copy(
          items = items
        )
      }
    }
  }

  override fun onCleared() {
    super.onCleared()
    job?.cancel()
  }

  companion object : MvRxViewModelFactory<StoryViewModel, StoryState> {

    override fun create(viewModelContext: ViewModelContext, state: StoryState): StoryViewModel {
      return StoryViewModel(
        state,
        (viewModelContext.activity.applicationContext as App).taskRepository
      )
    }
  }

}
