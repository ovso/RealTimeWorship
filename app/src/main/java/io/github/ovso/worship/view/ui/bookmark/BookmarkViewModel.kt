package io.github.ovso.worship.view.ui.bookmark

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.data.toBookmarkModels
import io.github.ovso.worship.data.toEntity
import io.github.ovso.worship.data.view.BookmarkModel
import io.github.ovso.worship.utils.rx.RxBus
import io.github.ovso.worship.utils.rx.RxBusEvent
import io.github.ovso.worship.utils.rx.SchedulerProvider
import io.github.ovso.worship.view.base.DisposableViewModel
import io.reactivex.rxjava3.kotlin.addTo
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import timber.log.Timber

class BookmarkViewModel constructor(
  private val owner: SavedStateRegistryOwner,
  private val repository: TasksRepository
) : DisposableViewModel() {

  private val _items = MutableLiveData<List<BookmarkModel>>()
  val items = _items

  init {
    reqBookmarks()
    observe()
  }

  private fun observe() {
    RxBus.listen(RxBusEvent.OnBookmarkItemDelClick::class.java).subscribe {
      val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
      }
      viewModelScope.launch(exceptionHandler) {
        val result = repository.delBookmark(it.item.toEntity())
        Timber.d("result = $result")
      }
    }.addTo(compositeDisposable)
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
