package com.example.shopping_list_app.data.db.savedlist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Insert as Insert1

@Dao
interface SavedListDao {
    @Insert1(onConflict = OnConflictStrategy.REPLACE)
    fun insert(savedList: SavedList)

    @Query("SELECT * FROM saved_list ORDER BY timestamp DESC")
    fun getAll(): LiveData<List<SavedList>>
}
