package io.github.ovso.worship.extensions

import androidx.activity.ComponentActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import io.github.ovso.worship.app.App
import io.github.ovso.worship.app.ViewModelFactory

fun Fragment.getViewModelFactory(): ViewModelFactory {
  val repository = (requireContext().applicationContext as App).taskRepository
  return ViewModelFactory(
    repository = repository,
    owner = this,
    defaultArgs = arguments
  )
}

fun ComponentActivity.getViewModelFactory(): ViewModelFactory {
  val repository = (applicationContext as App).taskRepository
  return ViewModelFactory(
    repository = repository,
    owner = this,
    intent = intent
  )
}
