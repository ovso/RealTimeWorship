package io.github.ovso.worship.view.ui.story

import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import io.github.ovso.worship.view.base.mvrx.MvRxViewModel
import timber.log.Timber

data class StoryState(
  val title: String = "ㅋㅋㅋ",
  val items: List<Any> = emptyList()
) : MvRxState

class StoryViewModel(initialState: StoryState, title: String) :
  MvRxViewModel<StoryState>(initialState) {


  init {
    Timber.d(title)
  }

  companion object : MvRxViewModelFactory<StoryViewModel, StoryState> {

    override fun create(viewModelContext: ViewModelContext, state: StoryState): StoryViewModel {
      return StoryViewModel(state, "ㅋㅋㅋ")
    }
  }
}
