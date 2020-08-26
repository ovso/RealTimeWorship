package io.github.ovso.worship.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import io.github.ovso.worship.BR

abstract class DataBindingFragment<T : ViewDataBinding>(
  @LayoutRes private val layoutRes: Int
) : Fragment() {
  protected lateinit var binding: T
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
    binding.setVariable(BR.viewModel, viewModel)
    return binding.root
  }

  abstract val viewModel: ViewModel
}
