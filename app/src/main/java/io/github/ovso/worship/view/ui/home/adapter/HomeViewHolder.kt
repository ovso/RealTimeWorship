package io.github.ovso.worship.view.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.worship.R
import io.github.ovso.worship.data.toVideoArgs
import io.github.ovso.worship.data.view.HomeItemModel
import io.github.ovso.worship.databinding.ItemHomeBinding

class HomeViewHolder(
  parent: ViewGroup
) : RecyclerView.ViewHolder(
  LayoutInflater.from(parent.context).inflate(
    R.layout.item_home,
    parent,
    false
  )
) {

  private val binding = DataBindingUtil.bind<ItemHomeBinding>(itemView)!!

  fun onBindViewHolder(item: HomeItemModel) {
    binding.item = item
    itemView.setOnClickListener {
      it.findNavController().navigate(
        R.id.videoFragment,
        bundleOf(
          "args" to item.toVideoArgs()
        )
      )
    }
  }
}
