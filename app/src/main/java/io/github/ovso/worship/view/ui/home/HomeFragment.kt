package io.github.ovso.worship.view.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import io.github.ovso.worship.R
import io.github.ovso.worship.databinding.FragmentHomeBinding
import io.github.ovso.worship.extensions.defaultDivider
import io.github.ovso.worship.extensions.getViewModelFactory
import io.github.ovso.worship.view.base.DataBindingFragment
import io.github.ovso.worship.view.ui.home.adapter.HomeAdapter

class HomeFragment : DataBindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
  override val viewModel: HomeViewModel by viewModels { getViewModelFactory() }

  private val adapter by lazy { HomeAdapter() }

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
    with(binding.rvHome) {
      defaultDivider()
      adapter = this@HomeFragment.adapter
    }
  }

}
