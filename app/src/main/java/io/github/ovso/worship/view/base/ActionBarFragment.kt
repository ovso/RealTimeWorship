package io.github.ovso.worship.view.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class ActionBarFragment : Fragment() {
  open fun showSupportActionBar() {
    (activity as? AppCompatActivity)?.supportActionBar?.show()
  }

  open fun hideSupportActionBar() {
    (activity as? AppCompatActivity)?.supportActionBar?.hide()
  }
}
