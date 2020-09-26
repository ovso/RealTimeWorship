package io.github.ovso.worship.view.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.ovso.worship.R
import io.github.ovso.worship.databinding.FragmentHomeBinding
import io.github.ovso.worship.extensions.defaultDivider
import io.github.ovso.worship.extensions.showBottomNav
import io.github.ovso.worship.view.base.DataBindingFragment
import io.github.ovso.worship.view.ui.home.adapter.HomeAdapter
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : DataBindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
  override val viewModel by viewModels<HomeViewModel>()

  @Inject
  lateinit var adapter: HomeAdapter

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
    binding.rvHome.defaultDivider()
    binding.rvHome.adapter = adapter
  }

  override fun onResume() {
    super.onResume()
    showBottomNav()
  }
}
