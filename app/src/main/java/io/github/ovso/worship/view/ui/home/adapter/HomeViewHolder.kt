package io.github.ovso.worship.view.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.worship.R
import io.github.ovso.worship.data.view.ChurchModel
import io.github.ovso.worship.databinding.ItemHomeBinding

class HomeViewHolder(private val navC: NavController, parent: ViewGroup) : RecyclerView.ViewHolder(
  LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
) {

  private val binding = DataBindingUtil.bind<ItemHomeBinding>(itemView)!!

  fun onBindViewHolder(item: ChurchModel) {
    binding.item = item
    itemView.setOnClickListener {
      navC.navigate(R.id.videoFragment, bundleOf("channel_id" to item.channelId))
/*
      val context = it.context
      val intent = Intent(context, VideoActivity::class.java).apply {
        putExtra("channel_id", item.channelId)
        putExtra("title", item.title)
      }
      context.startActivity(intent)
*/
    }
  }
}
