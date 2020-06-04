package io.github.ovso.worship.data.local.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryEntity(
  val id: Int,
  val title: String,
  val thumbnail: String,
  @PrimaryKey @NonNull val video_id: String
)
