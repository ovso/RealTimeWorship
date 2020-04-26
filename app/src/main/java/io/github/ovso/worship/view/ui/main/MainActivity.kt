package io.github.ovso.worship.view.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import io.github.ovso.worship.R
import io.github.ovso.worship.databinding.ActivityMainBinding
import io.github.ovso.worship.view.base.DataBindingActivity
import org.koin.android.ext.android.inject

class MainActivity : DataBindingActivity<ActivityMainBinding>(
    layoutResId = R.layout.activity_main,
    viewModelCls = MainViewModel::class.java
) {
    private val adapter: MainAdapter by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupAdapter()
        observe()
    }

    private fun setupAdapter() {
        with(binding.vpMain) {
            orientation = ViewPager2.ORIENTATION_VERTICAL
            adapter = this@MainActivity.adapter
        }
    }

    private fun observe() {
        val owner = this
        binding.viewModel?.items?.observe(owner, Observer {
//            adapter.submitList(it)
        })
        binding.viewModel?.message?.observe(owner, Observer {
            Toast.makeText(owner, it.toString(), Toast.LENGTH_SHORT).show()
        })
        binding.viewModel?.message?.observe(owner, Observer {
            AlertDialog.Builder(owner).setMessage(it.toString()).show()
        })
    }
}
