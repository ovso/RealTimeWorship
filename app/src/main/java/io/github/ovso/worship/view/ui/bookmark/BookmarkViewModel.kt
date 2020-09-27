package io.github.ovso.worship.view.ui.bookmark

import androidx.lifecycle.MutableLiveData
import androidx.savedstate.SavedStateRegistryOwner
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.data.toBookmarkModels
import io.github.ovso.worship.data.view.BookmarkModel
import io.github.ovso.worship.utils.rx.SchedulerProvider
import io.github.ovso.worship.view.base.DisposableViewModel
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber

class BookmarkViewModel constructor(
  private val owner: SavedStateRegistryOwner,
  private val repository: TasksRepository
) : DisposableViewModel() {

  private val _items = MutableLiveData<List<BookmarkModel>>()
  val items = _items

  init {
    reqBookmarks()
  }

  private fun reqBookmarks() {
    repository.getBookmarks().observe(owner, {
      it.toBookmarkModels()
        .subscribeOn(SchedulerProvider.io())
        .subscribe({ models ->
          _items.postValue(models)
        }, Timber::e)
        .addTo(compositeDisposable)
    })
  }
}
