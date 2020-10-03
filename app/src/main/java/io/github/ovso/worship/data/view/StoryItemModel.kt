package io.github.ovso.worship.data.view


import com.google.gson.annotations.SerializedName

data class StoryItemModel(
  @SerializedName("channel_id")
  val channelId: String,
  @SerializedName("title")
  val title: String
)
