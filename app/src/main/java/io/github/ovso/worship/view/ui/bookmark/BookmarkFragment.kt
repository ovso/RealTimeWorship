package io.github.ovso.worship.view.ui.bookmark

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import io.github.ovso.worship.R
import io.github.ovso.worship.databinding.FragmentBookmarkBinding
import io.github.ovso.worship.extensions.defaultDivider
import io.github.ovso.worship.extensions.showBottomNav
import io.github.ovso.worship.view.base.DataBindingFragmentWithoutVM
import io.github.ovso.worship.view.ui.bookmark.adapter.BookmarkAdapter
import javax.inject.Inject

@AndroidEntryPoint
class BookmarkFragment :
  DataBindingFragmentWithoutVM<FragmentBookmarkBinding>(R.layout.fragment_bookmark) {

  @Inject
  lateinit var adapter: BookmarkAdapter

  @Inject
  lateinit var viewModel: BookmarkViewModel

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupRv()
    observe()
  }

  private fun observe() {
    viewModel.items.observe(viewLifecycleOwner, {
      adapter.submitList(it)
    })
  }

  private fun setupRv() {
    with(binding.rvBookmark) {
      defaultDivider()
      adapter = BookmarkAdapter()
    }
    binding.rvBookmark.defaultDivider()
    binding.rvBookmark.adapter = adapter
  }

  override fun onResume() {
    super.onResume()
    showBottomNav()
  }
}
