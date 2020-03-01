package io.github.ovso.worship.view.ui.main

import io.github.ovso.worship.R
import io.github.ovso.worship.databinding.ActivityMainBinding
import io.github.ovso.worship.view.base.DataBindingActivity

class MainActivity : DataBindingActivity<ActivityMainBinding>(
    layoutResId = R.layout.activity_main,
    viewModelCls = MainViewModel::class.java
)
