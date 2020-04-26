package io.github.ovso.worship.view.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.ovso.worship.utils.event.Event
import io.github.ovso.worship.view.base.DisposableViewModel
import timber.log.Timber

class MainViewModel : DisposableViewModel() {

    private val _items = MutableLiveData<List<MainItem>>()
    val items: LiveData<List<MainItem>> = _items
    private val _message = MutableLiveData<Event<Boolean>>()
    val message: LiveData<Event<Boolean>> = _message

    init {
        _items.postValue(
            listOf(
                MainItem(
                    churchName = "삼일교회",
                    videoId = "hQbWYg02GX0"
                ),
                MainItem(
                    churchName = "분당우리교회",
                    videoId = "oGhmlQvPk3s"
                )
            )
        )
    }

    fun onClick(id: Int) {
//        _message.value = true
        _message.value = Event(true)
        Timber.i("id = $id")
    }
}
