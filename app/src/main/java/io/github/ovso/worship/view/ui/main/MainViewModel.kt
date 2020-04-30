package io.github.ovso.worship.view.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.view.base.DisposableViewModel
import org.jsoup.Jsoup
import timber.log.Timber

class MainViewModel(private val tasksRepository: TasksRepository) : DisposableViewModel() {

    private val _items = MutableLiveData<List<MainItem>>()
    val items: LiveData<List<MainItem>> = _items

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

        val body =
            Jsoup.parse("https://www.youtube.com/channel/UC6vNHBFM5VLNF53CKycyNZw/videos").body()
        println(body)
    }

    fun onClick(id: Int) {
        Timber.i("id = $id")
    }
}
