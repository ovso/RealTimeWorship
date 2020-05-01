package io.github.ovso.worship.view.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonElement
import io.github.ovso.worship.data.TasksRepository
import io.github.ovso.worship.view.base.DisposableViewModel
import org.jsoup.Jsoup
import org.jsoup.parser.Parser
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

        Thread {
            val get =
                Jsoup.connect("https://www.youtube.com/channel/UC6vNHBFM5VLNF53CKycyNZw/videos")
                    .get()

            /*

            {
  "items": [{"gridVideoRenderer":

            * */
            val elementsByTag = get.getElementsByTag("script")
//            val prefix = "{\"responseContext\""
            val prefix = "[{\"gridVideoRenderer"
//            val prefix = "\"items\": [{\"gridVideoRenderer\":"
            elementsByTag.forEach {
                if (it.data().contains(prefix)) {
                    val startIndex = it.data().indexOf(prefix)
                    val endIndex = it.data().indexOf(",\"continuations\"")
                    val subSequence = it.data().substring(startIndex, endIndex)
                    Gson().fromJson(subSequence, JsonElement::class.java)
                    Timber.d("subSequence = $subSequence")
                    //"items:[{gridVideoRenderer"
                }
            }
        }.start()
    }
    fun onClick(id: Int) {
        Timber.i("id = $id")
    }
}
