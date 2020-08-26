package io.github.ovso.worship.view.ui.bookmark

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import io.github.ovso.worship.R
import io.github.ovso.worship.databinding.FragmentBookmarkBinding
import io.github.ovso.worship.extensions.defaultDivider
import io.github.ovso.worship.extensions.getViewModelFactory
import io.github.ovso.worship.extensions.showBottomNav
import io.github.ovso.worship.view.base.DataBindingFragment
import io.github.ovso.worship.view.ui.bookmark.adapter.BookmarkAdapter

class BookmarkFragment : DataBindingFragment<FragmentBookmarkBinding>(R.layout.fragment_bookmark) {

  override val viewModel: BookmarkViewModel by viewModels { getViewModelFactory() }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupRv()
    observe()
  }

  private fun observe() {
    viewModel.items.observe(viewLifecycleOwner, Observer {
      (binding.rvBookmark.adapter as? BookmarkAdapter)?.submitList(it)
    })
  }

  private fun setupRv() {
    with(binding.rvBookmark) {
      defaultDivider()
      adapter = BookmarkAdapter()
    }
  }

  override fun onResume() {
    super.onResume()
    showBottomNav()
  }
}
