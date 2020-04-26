package io.github.ovso.worship.view.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import io.github.ovso.worship.R
import io.github.ovso.worship.extensions.getViewModelFactory

class MainFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel> { getViewModelFactory() }

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.items.observe(viewLifecycleOwner, Observer {
            AlertDialog.Builder(requireActivity()).setMessage(it.first().churchName).show()
        })
    }
}
