package io.github.ovso.worship.data.local.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookmarkDao {
  @Insert
  fun insert(repo: BookmarkEntity)

  @Insert
  fun insert(repos: List<BookmarkEntity>)

  @Query("delete from bookmark")
  fun removeAll()

  @Query("SELECT * FROM bookmark")
  fun bookmarks(): LiveData<List<BookmarkEntity>>
}
