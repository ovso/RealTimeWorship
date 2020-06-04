package io.github.ovso.worship.data.local.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {
  @Insert
  fun insert(entity: HistoryEntity)

  @Delete
  fun delete(entity: HistoryEntity): Int

  @Query("delete from history")
  fun removeAll()

  @Query("SELECT * FROM history")
  fun bookmarks(): LiveData<List<HistoryEntity>>
}
