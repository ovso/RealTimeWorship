package io.github.ovso.worship.view.ui.bookmark

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.savedstate.SavedStateRegistryOwner
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.data.mapper.toBookmarkModels
import io.github.ovso.worship.data.view.BookmarkModel
import io.github.ovso.worship.utils.SchedulerProvider
import io.github.ovso.worship.view.base.DisposableViewModel
import timber.log.Timber

class BookmarkViewModel(owner: SavedStateRegistryOwner, repository: TasksRepository) :
  DisposableViewModel() {

  private val _items = MutableLiveData<List<BookmarkModel>>()
  val items = _items

  init {
    repository.getBookmarks().observe(owner, Observer {
      it.toBookmarkModels()
        .subscribeOn(SchedulerProvider.io())
        .subscribe({ models ->
          _items.postValue(models)
        }, { t -> Timber.e(t) })
    })
  }
}
