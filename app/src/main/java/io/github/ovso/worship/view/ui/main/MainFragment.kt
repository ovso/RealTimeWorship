package io.github.ovso.worship.view.ui.main

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import io.github.ovso.worship.R
import io.github.ovso.worship.databinding.FragmentMainBinding
import io.github.ovso.worship.extensions.getViewModelFactory
import io.github.ovso.worship.extensions.toast
import io.github.ovso.worship.view.base.DataBindingFragment
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainFragment : DataBindingFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val adapter: MainAdapter by inject()
    override val viewModel by viewModels<MainViewModel> { getViewModelFactory() }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        val channelId = arguments?.getString("channel_id")
        setupRecyclerView()
        observe()
        rv_main.setOnClickListener {
            toast("ㅋㅋㅋㅋㅋㅋㅋㅋㅋ")
        }
        toast(arguments?.getString("channel_id"))
    }

    private fun setupRecyclerView() {
        rv_main.adapter = adapter
    }

    private fun observe() {
        viewModel.getItems().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
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
    }
}
