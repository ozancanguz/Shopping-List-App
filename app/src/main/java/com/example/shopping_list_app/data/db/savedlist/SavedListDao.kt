package com.example.shopping_list_app.data.db.savedlist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SavedListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(savedList: SavedList)

    @Query("SELECT * FROM saved_lists ORDER BY time DESC")
    fun getAllSavedLists(): LiveData<List<SavedList>>
}
