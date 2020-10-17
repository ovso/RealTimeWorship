package io.github.ovso.worship.view.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.ovso.view.NativeAdsDialog2
import io.github.ovso.worship.R
import io.github.ovso.worship.databinding.FragmentHomeBinding
import io.github.ovso.worship.extensions.defaultDivider
import io.github.ovso.worship.extensions.showBottomNav
import io.github.ovso.worship.view.base.DataBindingFragment
import io.github.ovso.worship.view.ui.home.adapter.HomeAdapter
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : DataBindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
  override val viewModel by viewModels<HomeViewModel>()

  @Inject
  lateinit var adapter: HomeAdapter

  private lateinit var callback: OnBackPressedCallback

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupRv()
    observe()
  }

  private fun observe() {
    viewModel.items.observe(viewLifecycleOwner, {
      adapter.submitList(it)
    })
  }

  private fun setupRv() {
    binding.rvHome.defaultDivider()
    binding.rvHome.adapter = adapter
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    callback = object : OnBackPressedCallback(true) {
      override fun handleOnBackPressed() {
        NativeAdsDialog2(context)
          .setUnitId(getString(R.string.ads_native_unit_id))
          .setTitle(R.string.dialog_title_exit)
          .setPositiveButton(R.string.dialog_exit_yes_btn) { dialog, _ ->
            requireActivity().finish()
          }.setNeutralButton(R.string.dialog_exit_no_btn) { dialog, _ ->
            dialog.dismiss()
          }.show()
      }
    }
    requireActivity().onBackPressedDispatcher.addCallback(this, callback)
  }

  override fun onResume() {
    super.onResume()
    showBottomNav()
  }

  override fun onDetach() {
    super.onDetach()
    callback.remove()
  }
}
