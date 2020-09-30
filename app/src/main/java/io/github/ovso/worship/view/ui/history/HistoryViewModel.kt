package io.github.ovso.worship.view.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import io.github.ovso.worship.R
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.data.toHistoryModels
import io.github.ovso.worship.data.view.HistoryModel
import io.github.ovso.worship.view.base.DisposableViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HistoryViewModel(
  private val owner: SavedStateRegistryOwner,
  private val repository: TasksRepository,
) : DisposableViewModel() {

  private val _items = MutableLiveData<List<HistoryModel>>()
  val items = _items

  private var job: Job? = null
  private val _toast = MutableLiveData<Int>()
  val toast: LiveData<Int> = _toast

  init {
    reqHistory()
  }

  private fun reqHistory() {
    val handler = CoroutineExceptionHandler { _, _ ->
      _toast.value = R.string.all_error
    }
    repository.getHistories().observe(owner) {
      job = viewModelScope.launch(handler) {
        _items.value = it.toHistoryModels()
      }
    }
  }

  override fun onCleared() {
    super.onCleared()
    job?.cancel()
  }
}
