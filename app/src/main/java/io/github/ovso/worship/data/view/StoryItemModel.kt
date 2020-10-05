package io.github.ovso.worship.data.view


import com.google.gson.annotations.SerializedName

data class StoryItemModel(
  @SerializedName("id")
  val id: String,
  @SerializedName("title")
  val title: String,
  @SerializedName("category")
  val category: String
)
