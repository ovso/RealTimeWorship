package io.github.ovso.worship.view.ui.history

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.ovso.worship.R
import io.github.ovso.worship.databinding.FragmentHistoryBinding
import io.github.ovso.worship.extensions.defaultDivider
import io.github.ovso.worship.extensions.getViewModelFactory
import io.github.ovso.worship.extensions.showBottomNav
import io.github.ovso.worship.view.base.DataBindingFragment
import io.github.ovso.worship.view.ui.history.adapter.HistoryAdapter
import javax.inject.Inject

@AndroidEntryPoint
class HistoryFragment : DataBindingFragment<FragmentHistoryBinding>(R.layout.fragment_history) {

  @Inject
  lateinit var adapter: HistoryAdapter

  override val viewModel by viewModels<HistoryViewModel> { getViewModelFactory() }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupRv()
    observe()
  }

  private fun observe() {
    viewModel.items.observe(viewLifecycleOwner, {
      adapter.submitList(it)
    })
    viewModel.toast.observe(viewLifecycleOwner) {
      Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }
  }

  private fun setupRv() {
    binding.rvHistory.defaultDivider()
    binding.rvHistory.adapter = adapter
  }

  override fun onResume() {
    super.onResume()
    showBottomNav()
  }
}
