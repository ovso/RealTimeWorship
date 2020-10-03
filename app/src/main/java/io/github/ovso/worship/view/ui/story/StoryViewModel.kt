package io.github.ovso.worship.view.ui.story

import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import io.github.ovso.worship.app.App
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.view.base.mvrx.MvRxViewModel

data class StoryState(
  val title: String = "ㅋㅋㅋ",
  val items: List<Any> = emptyList()
) : MvRxState

class StoryViewModel(
  private val initialState: StoryState,
  private val repository: TasksRepository
) : MvRxViewModel<StoryState>(initialState) {

  init {

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
