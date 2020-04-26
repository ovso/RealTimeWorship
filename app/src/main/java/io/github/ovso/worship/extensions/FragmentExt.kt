package io.github.ovso.worship.extensions

import androidx.fragment.app.Fragment
import io.github.ovso.worship.app.App
import io.github.ovso.worship.app.ViewModelFactory

fun Fragment.getViewModelFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as App).taskRepository
    return ViewModelFactory(repository, this)
}
