package com.example.shopping_list_app.data.db.savedlist

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shopping_list_app.data.db.item.Item

@Entity(tableName = "saved_list")
data class SavedList(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val itemList: List<Item>,
    val timestamp: Long
)
