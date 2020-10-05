package io.github.ovso.worship.data.dto


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VideoDto(
  @SerializedName("id")
  val id: String,
  @SerializedName("title")
  val title: String,
  @SerializedName("category")
  val category: String
): Parcelable
