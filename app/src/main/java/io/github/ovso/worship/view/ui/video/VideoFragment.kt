package io.github.ovso.worship.view.ui.video

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.facebook.shimmer.ShimmerFrameLayout
import io.github.ovso.worship.R
import io.github.ovso.worship.databinding.FragmentVideoBinding
import io.github.ovso.worship.extensions.defaultDivider
import io.github.ovso.worship.extensions.getViewModelFactory
import io.github.ovso.worship.extensions.hideBottomNav
import io.github.ovso.worship.extensions.setTitle
import io.github.ovso.worship.view.base.DataBindingFragment
import io.github.ovso.worship.view.ui.video.adapter.VideoAdapter

class VideoFragment :
  DataBindingFragment<FragmentVideoBinding>(R.layout.fragment_video) {

  override val viewModel by viewModels<VideoViewModel> { getViewModelFactory() }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupRecyclerView()
    observe()
  }

  private fun startShimmer() {
    val root = binding.root as? ViewGroup
    val view =
      LayoutInflater.from(context).inflate(R.layout.view_all_list_shimmer, root, false)
    root?.addView(view)
  }

  private fun setupRecyclerView() {
    with(binding.rvVideo) {
      adapter = VideoAdapter()
      defaultDivider()
    }
  }

  private fun observe() {
    viewModel.items.observe(viewLifecycleOwner, Observer {
      (binding.rvVideo.adapter as? VideoAdapter)?.submitList(it)
    })
    viewModel.isLoading.observe(viewLifecycleOwner, Observer {
      when (it) {
        true -> startShimmer()
        else -> stopShimmer()
      }
    })
    viewModel.title.observe(viewLifecycleOwner, Observer { setTitle(it) })
  }

  private fun stopShimmer() {
    val root = binding.root as? ViewGroup
    val shimmerFrameLayout = root?.getChildAt(1) as? ShimmerFrameLayout
    shimmerFrameLayout?.let {
      it.stopShimmer()
      root.removeView(shimmerFrameLayout)
    }
  }

  companion object {
    fun newInstance(channelId: String): VideoFragment {
      val fragment = VideoFragment()
      fragment.arguments = bundleOf("channel_id" to channelId)
      return fragment
    }
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    hideBottomNav()
  }
}
