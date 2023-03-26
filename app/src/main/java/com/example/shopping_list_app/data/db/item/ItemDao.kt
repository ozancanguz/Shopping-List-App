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


    @Query("SELECT * FROM itemlist_table WHERE itemName = :itemName")
    suspend fun get(itemName: String): Item?



    @Transaction
    suspend fun insertOrUpdate(item: Item) {
        // Check if an item with the same name already exists
        val existingItem = getItemByName(item.itemName)
        if (existingItem == null) {
            // If not, insert the new item
            insertData(item)
        } else {
            // If it exists, update its quantity by incrementing it by 1
            val newItem = Item(existingItem.id, existingItem.itemName, existingItem.amount + 1)
            updateData(newItem)
        }
    }

    @Query("SELECT * FROM itemlist_table WHERE itemName = :itemName")
    suspend fun getItemByName(itemName: String): Item?

    @Update
    suspend fun updateData(item: Item)
}