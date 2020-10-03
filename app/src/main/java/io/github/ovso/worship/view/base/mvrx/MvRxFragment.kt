package io.github.ovso.worship.view.base.mvrx

import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.MvRx

abstract class MvRxFragment(@LayoutRes val layoutId: Int) : BaseMvRxFragment(layoutId) {
  protected fun navigateTo(@IdRes actionId: Int, arg: Parcelable? = null) {
    /**
     * If we put a parcelable arg in [MvRx.KEY_ARG] then MvRx will attempt to call a secondary
     * constructor on any MvRxState objects and pass in this arg directly.
     * @see [com.airbnb.mvrx.sample.features.dadjoke.DadJokeDetailState]
     */
    val bundle = arg?.let { Bundle().apply { putParcelable(MvRx.KEY_ARG, it) } }
    findNavController().navigate(actionId, bundle)
  }
}
