package io.github.ovso.worship.view.ui.list

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import io.github.ovso.worship.R
import io.github.ovso.worship.databinding.FragmentMainBinding
import io.github.ovso.worship.extensions.getViewModelFactory
import io.github.ovso.worship.extensions.toast
import io.github.ovso.worship.view.base.DataBindingFragment
import timber.log.Timber

class MainFragment : DataBindingFragment<FragmentMainBinding>(R.layout.fragment_main) {

    override val viewModel by viewModels<MainViewModel> { getViewModelFactory() }

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.items.observe(viewLifecycleOwner, Observer {
            val iterator = it.iterator()
            while (iterator.hasNext()) {
                Timber.d(iterator.next().churchName)
            }
            toast("onActivityCreated")
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
