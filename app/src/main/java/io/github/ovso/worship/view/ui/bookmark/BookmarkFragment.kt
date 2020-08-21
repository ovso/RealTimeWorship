package io.github.ovso.worship.view.ui.bookmark

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import io.github.ovso.worship.R
import io.github.ovso.worship.extensions.defaultDivider
import io.github.ovso.worship.extensions.getViewModelFactory
import io.github.ovso.worship.view.ui.bookmark.adapter.BookmarkAdapter
import kotlinx.android.synthetic.main.fragment_bookmark.*

class BookmarkFragment : Fragment(R.layout.fragment_bookmark) {

  private val viewModel: BookmarkViewModel by viewModels { getViewModelFactory() }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupRv()
    observe()
  }

  private fun observe() {
    viewModel.items.observe(viewLifecycleOwner, Observer {
      (rv_bookmark.adapter as? BookmarkAdapter)?.submitList(it)
    })
  }

  private fun setupRv() {
    with(rv_bookmark) {
      defaultDivider()
      adapter = BookmarkAdapter()
    }
  }
}
