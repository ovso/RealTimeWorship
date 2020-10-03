package io.github.ovso.worship.data.local.model


import com.google.gson.annotations.SerializedName

data class StoryEntity(
  @SerializedName("channel_id")
  val channelId: String,
  @SerializedName("title")
  val title: String
)
