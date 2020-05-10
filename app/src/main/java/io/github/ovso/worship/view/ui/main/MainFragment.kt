package io.github.ovso.worship.view.ui.main

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import io.github.ovso.worship.R
import io.github.ovso.worship.databinding.FragmentMainBinding
import io.github.ovso.worship.extensions.getViewModelFactory
import io.github.ovso.worship.view.base.DataBindingFragment
import timber.log.Timber

class MainFragment : DataBindingFragment<FragmentMainBinding>(R.layout.fragment_main) {

    override val viewModel by viewModels<MainViewModel> { getViewModelFactory() }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val channelId = arguments?.getString("channel_id")
        viewModel.getItems().observe(viewLifecycleOwner, Observer {
            val iterator = it.iterator()
            while (iterator.hasNext()) {
                Timber.d(iterator.next().churchName)
            }
        })
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (isEnabled) requireActivity().finishAffinity()
                }

            })
    }
}
