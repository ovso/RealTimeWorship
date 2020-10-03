package io.github.ovso.worship.view.ui.story

import android.os.Bundle
import android.view.View
import com.airbnb.mvrx.fragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.github.ovso.worship.R
import io.github.ovso.worship.databinding.StoryFragmentBinding
import io.github.ovso.worship.extensions.defaultDivider
import io.github.ovso.worship.view.base.mvrx.MvRxFragment
import io.github.ovso.worship.view.base.viewBinding
import io.github.ovso.worship.view.ui.story.adapter.StoryAdapter
import javax.inject.Inject

@AndroidEntryPoint
class StoryFragment : MvRxFragment(R.layout.story_fragment) {

  private val binding by viewBinding(StoryFragmentBinding::bind)

  private val viewModel: StoryViewModel by fragmentViewModel()

  @Inject
  lateinit var adapter: StoryAdapter

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupRv()
    subscriber()
  }

  private fun setupRv() {
    with(binding.rvStory) {
      adapter = this@StoryFragment.adapter
      defaultDivider()
    }
  }

  private fun subscriber() {
    val owner = viewLifecycleOwner
    viewModel.selectSubscribe(owner, StoryState::items) {
      adapter.submitList(it)
    }
  }
}
