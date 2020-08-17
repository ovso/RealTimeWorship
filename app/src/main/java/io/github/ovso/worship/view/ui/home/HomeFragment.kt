package io.github.ovso.worship.view.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.worship.R
import io.github.ovso.worship.data.view.HomeItemModel
import io.github.ovso.worship.databinding.FragmentHomeBinding
import io.github.ovso.worship.extensions.getViewModelFactory
import io.github.ovso.worship.view.base.DataBindingFragment
import io.github.ovso.worship.view.ui.home.adapter.HomeAdapter

class HomeFragment : DataBindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
  override val viewModel: HomeViewModel by viewModels { getViewModelFactory() }

  private val adapter by lazy {
    HomeAdapter(onItemClickListener)
  }

  private val onItemClickListener: ((HomeItemModel) -> Unit) = {
    findNavController().navigate(R.id.videoFragment, bundleOf("channel_id" to it.channelId))
  }

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
    binding.rvHome.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
    with(binding.rvHome) {
      addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
      adapter = this@HomeFragment.adapter
    }
  }

}
