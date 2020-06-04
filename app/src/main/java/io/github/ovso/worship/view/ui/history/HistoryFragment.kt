package io.github.ovso.worship.view.ui.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.worship.R
import io.github.ovso.worship.extensions.getViewModelFactory
import io.github.ovso.worship.view.ui.history.adapter.HistoryAdapter
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : Fragment(R.layout.fragment_history) {

  private val viewModel: HistoryViewModel by viewModels { getViewModelFactory() }

  private val adapter by lazy { HistoryAdapter() }

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
    rv_history.adapter = adapter
    rv_history.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
  }

}
