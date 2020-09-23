package io.github.ovso.worship.view.ui.history

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.data.toHistoryModels
import io.github.ovso.worship.data.view.HistoryModel
import io.github.ovso.worship.view.base.DisposableViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HistoryViewModel(
  private val owner: LifecycleOwner,
  private val repository: TasksRepository,
) : DisposableViewModel() {

  private val _items = MutableLiveData<List<HistoryModel>>()
  val items = _items

  private var job: Job? = null

  init {
    reqHistory()
  }

  private fun reqHistory() {
    job = viewModelScope.launch {
      val toHistoryModels = repository.getHistoriesAsync().toHistoryModels()
      _items.value = toHistoryModels
    }
  }

  override fun onCleared() {
    super.onCleared()
    job?.cancel()
  }
}
