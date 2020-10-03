package io.github.ovso.worship.view.base.mvrx

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxState
import io.github.ovso.worship.BuildConfig

abstract class MvRxViewModel<S : MvRxState>(initialState: S) :
  BaseMvRxViewModel<S>(initialState, debugMode = BuildConfig.DEBUG)
