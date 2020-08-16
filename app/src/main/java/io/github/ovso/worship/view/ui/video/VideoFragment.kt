package io.github.ovso.worship.view.ui.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.github.ovso.worship.R
import io.github.ovso.worship.databinding.FragmentMainBinding
import io.github.ovso.worship.extensions.getViewModelFactory
import io.github.ovso.worship.view.base.DataBindingFragment
import io.github.ovso.worship.view.ui.video.adapter.MainAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.ext.android.inject
import timber.log.Timber

class VideoFragment:
  DataBindingFragment<FragmentMainBinding>(R.layout.fragment_main) {

  private val bnvMain by lazy {
    activity?.findViewById<BottomNavigationView>(R.id.nav_view)
  }

  companion object {
    fun newInstance(channelId: String): VideoFragment {
      val fragment = VideoFragment()
      fragment.arguments = bundleOf("channel_id" to channelId)
      return fragment
    }
  }

  private val adapter: MainAdapter by inject()
  override val viewModel by viewModels<VideoViewModel> { getViewModelFactory() }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupRecyclerView()
    observe()
    bnvMain?.isVisible = false
  }

  private fun startShimmer() {
    val root = binding.root as? ViewGroup
    val view =
      LayoutInflater.from(context).inflate(R.layout.view_all_list_shimmer, root, false)
    root?.addView(view)
  }

  private fun setupRecyclerView() {
    rv_main.adapter = adapter
    rv_main.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
  }

  private fun observe() {
    viewModel.items.observe(viewLifecycleOwner, Observer {
      adapter.submitList(it)
    })
    viewModel.isLoading.observe(viewLifecycleOwner, Observer {
      when (it) {
        true -> startShimmer()
        else -> stopShimmer()
      }
    })
  }

  private fun stopShimmer() {
    val root = binding.root as? ViewGroup
    val shimmerFrameLayout = root?.getChildAt(1) as? ShimmerFrameLayout
    shimmerFrameLayout?.stopShimmer()
    shimmerFrameLayout?.let { root.removeView(shimmerFrameLayout) }
  }

  override fun onDestroy() {
    super.onDestroy()
    Timber.d("onDestroy()")
  }

  override fun onDetach() {
    super.onDetach()
    Timber.d("onDetach()")
  }

  override fun onDestroyView() {
    super.onDestroyView()
    Timber.d("onDestroyView()")
    adapter.submitList(listOf())
    bnvMain?.isVisible = true
  }
}
