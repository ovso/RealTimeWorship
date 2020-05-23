package io.github.ovso.worship.view.ui.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.worship.R
import io.github.ovso.worship.data.view.HomeModel
import io.github.ovso.worship.databinding.ItemHomeBinding
import io.github.ovso.worship.view.ui.player.PlayerActivity

class HomeViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
  LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
) {

  private val binding = DataBindingUtil.bind<ItemHomeBinding>(itemView)!!

  fun onBindViewHolder(item: HomeModel) {
    binding.item = item
    itemView.setOnClickListener {
      val context = it.context
      val intent = Intent(context, PlayerActivity::class.java).apply {
        putExtra("title", item.title)
      }
      context.startActivity(intent)
    }
  }
}
