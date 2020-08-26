package io.github.ovso.worship.view.ui.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import io.github.ovso.worship.R
import io.github.ovso.worship.databinding.FragmentHistoryBinding
import io.github.ovso.worship.extensions.defaultDivider
import io.github.ovso.worship.extensions.getViewModelFactory
import io.github.ovso.worship.extensions.showBottomNav
import io.github.ovso.worship.view.base.DataBindingFragment
import io.github.ovso.worship.view.ui.history.adapter.HistoryAdapter
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : DataBindingFragment<FragmentHistoryBinding>(R.layout.fragment_history) {

  override val viewModel: HistoryViewModel by viewModels { getViewModelFactory() }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupRv()
    observe()
  }

  private fun observe() {
    viewModel.items.observe(viewLifecycleOwner, Observer {
      (rv_history.adapter as? HistoryAdapter)?.submitList(it)
    })
  }

  private fun setupRv() {
    rv_history.apply {
      defaultDivider()
      adapter = HistoryAdapter()
    }
  }

  override fun onResume() {
    super.onResume()
    showBottomNav()
  }
}
