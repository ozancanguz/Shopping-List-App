package com.example.shopping_list_app.data.db.history

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HistoryDao {

    @Query("select * from historyItems_table order by id Asc")
    fun getAllHistoryItems(): LiveData<List<HistoryItem>>



    // insert item
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHistoryItem(historyItem: HistoryItem)

    @Delete
    suspend fun deleteHistoryItem(historyItem: HistoryItem)

    @Query("delete from historyItems_table")
    suspend fun deleteAllHistoryItem()




}
