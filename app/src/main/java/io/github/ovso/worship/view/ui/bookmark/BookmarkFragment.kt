package io.github.ovso.worship.view.ui.bookmark

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.worship.R
import io.github.ovso.worship.extensions.getViewModelFactory
import io.github.ovso.worship.view.ui.bookmark.adapter.BookmarkAdapter
import kotlinx.android.synthetic.main.fragment_bookmark.*

class BookmarkFragment : Fragment(R.layout.fragment_bookmark) {

  private val viewModel: BookmarkViewModel by viewModels { getViewModelFactory() }

  private val adapter by lazy { BookmarkAdapter() }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupRv()
    observe()
  }

  private fun observe() {
    viewModel.items.observe(viewLifecycleOwner, Observer {
      adapter.submitList(it)
    })
  }

  private fun setupRv() {
    rv_bookmark.adapter = adapter
    rv_bookmark.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
  }
}
