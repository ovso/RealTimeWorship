package io.github.ovso.worship.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.ovso.worship.data.local.model.BookmarkDao
import io.github.ovso.worship.data.local.model.BookmarkEntity

//@Database(entities = [BookmarkEnity::class, HistoryEnity::class], version = 1)
@Database(entities = [BookmarkEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
  abstract fun bookmarkDao(): BookmarkDao
//  abstract fun histories(): StargazerDao
}
