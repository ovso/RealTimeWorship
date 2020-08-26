package io.github.ovso.worship.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.showSupportActionBar() {
  (activity as? AppCompatActivity)?.supportActionBar?.show()
}

fun Fragment.hideSupportActionBar() {
  (activity as? AppCompatActivity)?.supportActionBar?.hide()
}

fun Fragment.setTitle(title: String) {
  (activity as? AppCompatActivity)?.supportActionBar?.title = title
}
