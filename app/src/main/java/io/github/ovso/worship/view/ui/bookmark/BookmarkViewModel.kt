package io.github.ovso.worship.view.ui.bookmark

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.data.toBookmarkModels
import io.github.ovso.worship.data.view.BookmarkModel
import io.github.ovso.worship.view.base.DisposableViewModel
import kotlinx.coroutines.launch

class BookmarkViewModel @ViewModelInject constructor(
  private val repository: TasksRepository
) : DisposableViewModel() {

  private val _items = MutableLiveData<List<BookmarkModel>>()
  val items = _items

  init {
    viewModelScope.launch {
      _items.value = repository.getBookmarksAsync().toBookmarkModels()
    }
  }
}
