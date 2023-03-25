package com.example.shopping_list_app.data.db.item

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ItemDao {

    @Query("select * from itemlist_table order by id Asc")
    fun getAllItems(): LiveData<List<Item>>



    // insert item
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("delete from itemlist_table")
    suspend fun deleteAll()



}