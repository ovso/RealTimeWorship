package io.github.ovso.worship.data.remote.response

import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("gridVideoRenderer")
    val gridVideoRenderer: GridVideoRenderer
)

data class GridVideoRenderer(
    @SerializedName("publishedTimeText")
    val publishedTimeText: PublishedTimeText,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @SerializedName("title")
    val title: Title,
    @SerializedName("videoId")
    val videoId: String,
    @SerializedName("viewCountText")
    val viewCountText: ViewCountText
)

data class PublishedTimeText(
    @SerializedName("simpleText")
    val simpleText: String
)

data class Thumbnail(
    @SerializedName("thumbnails")
    val thumbnails: List<ThumbnailX>
)

data class Title(
    @SerializedName("simpleText")
    val simpleText: String
)

data class ViewCountText(
    @SerializedName("simpleText")
    val simpleText: String
)

data class ThumbnailX(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)
