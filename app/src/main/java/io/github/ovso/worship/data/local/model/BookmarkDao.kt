package io.github.ovso.worship.data.local.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookmarkDao {
  @Insert
  fun insert(repo: BookmarkEntity)

  @Delete
  fun delete(repo: BookmarkEntity): Int

  @Query("delete from bookmark")
  fun removeAll()

  @Query("SELECT * FROM bookmark")
  fun bookmarks(): LiveData<List<BookmarkEntity>>

  @Query("SELECT * FROM bookmark WHERE video_id LIKE :videoId")
  fun getBookmark(videoId: String): LiveData<BookmarkEntity?>
}
