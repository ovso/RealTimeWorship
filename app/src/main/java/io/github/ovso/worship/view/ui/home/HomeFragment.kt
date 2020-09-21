package io.github.ovso.worship.view.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import io.github.ovso.worship.R
import io.github.ovso.worship.databinding.FragmentHomeBinding
import io.github.ovso.worship.extensions.defaultDivider
import io.github.ovso.worship.extensions.showBottomNav
import io.github.ovso.worship.view.base.DataBindingFragment
import io.github.ovso.worship.view.ui.home.adapter.HomeAdapter

@AndroidEntryPoint
class HomeFragment : DataBindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
  override val viewModel by viewModels<HomeViewModel>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupRv()
    observe()
  }

  private fun observe() {
    viewModel.items.observe(viewLifecycleOwner, Observer {
      (binding.rvHome.adapter as? HomeAdapter)?.submitList(it)
    })
  }

  private fun setupRv() {
    with(binding.rvHome) {
      defaultDivider()
      adapter = HomeAdapter()
    }
  }

  override fun onResume() {
    super.onResume()
    showBottomNav()
  }
}
