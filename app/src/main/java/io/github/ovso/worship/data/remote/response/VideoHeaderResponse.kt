package io.github.ovso.worship.data.remote.response


import com.google.gson.annotations.SerializedName

data class VideoHeaderResponse(
  @SerializedName("avatar")
  val avatar: Avatar? = null,
  @SerializedName("title")
  val title: String? = null
)

data class Avatar(
  @SerializedName("thumbnails")
  val thumbnails: List<Thumbnail>
)
