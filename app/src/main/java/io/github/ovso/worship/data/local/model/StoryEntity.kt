package io.github.ovso.worship.data.local.model


import com.google.gson.annotations.SerializedName

data class StoryEntity(
  @SerializedName("id")
  val id: String,
  @SerializedName("title")
  val title: String
)
