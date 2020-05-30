package io.github.ovso.worship.data.local.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {
  @Insert
  fun insert(repo: HistoryEntity)

  @Insert
  fun insert(repos: List<HistoryEntity>)

  @Query("delete from history")
  fun removeAll()

  @Query("SELECT * FROM history")
  fun bookmarks(): LiveData<List<HistoryEntity>>
}
