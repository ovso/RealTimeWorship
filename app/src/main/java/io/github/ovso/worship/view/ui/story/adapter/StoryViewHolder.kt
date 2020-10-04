package io.github.ovso.worship.view.ui.story.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.worship.R
import io.github.ovso.worship.data.view.StoryItemModel
import io.github.ovso.worship.databinding.ItemStoryBinding

class StoryViewHolder private constructor(
  private val binding: ItemStoryBinding
) : RecyclerView.ViewHolder(binding.root) {

  fun onBindViewHolder(item: StoryItemModel) {
    binding.tvStory.text = item.title
    binding.root.setOnClickListener {
      it.findNavController().navigate(
        R.id.videoFragment,
        bundleOf(
          "channel_id" to item.id,
          "title" to item.title
        )
      )
    }
/*
    itemView.setOnClickListener {
      it.findNavController().navigate(
        R.id.videoFragment,
        bundleOf("channel_id" to item.channelId, "title" to item.title)
      )
    }
*/
  }

  companion object {
    fun create(parent: ViewGroup): StoryViewHolder {
      return StoryViewHolder(
        ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      )
    }
  }
}
