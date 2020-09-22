package io.github.ovso.worship.view.ui.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.AndroidEntryPoint
import io.github.ovso.worship.R
import io.github.ovso.worship.databinding.FragmentHistoryBinding
import io.github.ovso.worship.di.TestClass
import io.github.ovso.worship.extensions.defaultDivider
import io.github.ovso.worship.extensions.showBottomNav
import io.github.ovso.worship.view.base.DataBindingFragment
import io.github.ovso.worship.view.ui.history.adapter.HistoryAdapter
import kotlinx.android.synthetic.main.fragment_history.*
import javax.inject.Inject

@AndroidEntryPoint
class HistoryFragment : DataBindingFragment<FragmentHistoryBinding>(R.layout.fragment_history) {

  @Inject
  lateinit var testClass: TestClass

  @Inject
  lateinit var life: LifecycleOwner

  override val viewModel by viewModels<HistoryViewModel>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupRv()
    observe()
  }

  private fun observe() {
    viewModel.items.observe(viewLifecycleOwner, {
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
