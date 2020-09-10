package io.github.ovso.worship.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.ovso.worship.data.local.model.BookmarkDao
import io.github.ovso.worship.data.local.model.BookmarkEntity
import io.github.ovso.worship.data.local.model.HistoryDao
import io.github.ovso.worship.data.local.model.HistoryEntity

@Database(entities = [BookmarkEntity::class, HistoryEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
  abstract fun bookmarkDao(): BookmarkDao
  abstract fun historyDao(): HistoryDao
}
