package io.github.ovso.worship.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
data class BookmarkEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Int = 0,
  val title: String,
  val thumbnail: String,
  val video_id: String
)
