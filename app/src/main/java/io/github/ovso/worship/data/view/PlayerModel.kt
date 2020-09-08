package io.github.ovso.worship.data.view

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlayerModel(
  val thumbnail: String,
  val videoId: String,
  val title: String
) : Parcelable
