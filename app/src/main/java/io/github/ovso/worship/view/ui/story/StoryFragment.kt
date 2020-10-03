package io.github.ovso.worship.view.ui.story

import android.os.Bundle
import android.view.View
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.fragmentViewModel
import io.github.ovso.worship.R
import io.github.ovso.worship.databinding.StoryFragmentBinding
import io.github.ovso.worship.view.base.viewBinding

class StoryFragment : BaseMvRxFragment(R.layout.story_fragment) {

  private val binding by viewBinding(StoryFragmentBinding::bind)

  private val viewModel: StoryViewModel by fragmentViewModel(StoryViewModel::class)

  override fun invalidate() {

  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

  }
}
