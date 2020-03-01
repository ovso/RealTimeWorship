package io.github.ovso.worship.view.ui.main

import com.google.gson.annotations.SerializedName

data class MainItem(
    @SerializedName("churchName")
    val churchName: String,
    @SerializedName("videoId")
    val videoId: String
)
