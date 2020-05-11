package io.github.ovso.worship.view.ui.home

import androidx.fragment.app.viewModels
import io.github.ovso.worship.R
import io.github.ovso.worship.databinding.FragmentHomeBinding
import io.github.ovso.worship.extensions.getViewModelFactory
import io.github.ovso.worship.view.base.DataBindingFragment

class HomeFragment : DataBindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override val viewModel by viewModels<HomeViewModel> { getViewModelFactory() }

}
