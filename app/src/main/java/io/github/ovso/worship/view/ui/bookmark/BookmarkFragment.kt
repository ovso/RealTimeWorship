package io.github.ovso.worship.view.ui.bookmark

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import io.github.ovso.worship.R
import io.github.ovso.worship.extensions.toast

class BookmarkFragment : Fragment(R.layout.fragment_bookmark) {

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    toast("onViewCreated")
  }
}
