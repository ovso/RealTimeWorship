package io.github.ovso.worship.data.local.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {
  @Insert
  suspend fun addHistory(entity: HistoryEntity)

  @Delete
  fun delete(entity: HistoryEntity): Int

  @Query("delete from history")
  fun removeAll()

  @Query("SELECT * FROM history")
  fun histories(): LiveData<List<HistoryEntity>>

  @Query("SELECT * FROM history")
  suspend fun historiesAsync(): List<HistoryEntity>

  @Query("SELECT * FROM history WHERE video_id LIKE :videoId")
  fun getHistory(videoId: String): LiveData<HistoryEntity?>

}
