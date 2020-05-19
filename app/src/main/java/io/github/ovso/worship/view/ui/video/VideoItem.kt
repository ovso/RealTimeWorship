package io.github.ovso.worship.view.ui.video

import com.google.gson.annotations.SerializedName

data class VideoItem(
    @SerializedName("churchName")
    val churchName: String,
    @SerializedName("videoId")
    val videoId: String
)
