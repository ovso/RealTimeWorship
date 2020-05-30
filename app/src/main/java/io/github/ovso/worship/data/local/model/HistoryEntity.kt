package io.github.ovso.worship.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Int,
  val title: String,
  val thumbnail: String,
  val video_id: String
)
