package io.github.ovso.worship.view.ui.story

import androidx.fragment.app.Fragment
import io.github.ovso.worship.R
import io.github.ovso.worship.databinding.StoryFragmentBinding
import io.github.ovso.worship.view.base.viewBinding

class StoryFragment : Fragment(R.layout.story_fragment) {

  private lateinit var viewModel: StoryViewModel

  private val binding by viewBinding(StoryFragmentBinding::bind)
}
