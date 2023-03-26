package com.example.shopping_list_app.data.db.savedList

import androidx.room.Entity
import com.example.shopping_list_app.data.db.item.Item

@Entity(tableName = "saved_lists ")
data class SavedList(
    val time: Long,
    val items: List<Item>
)
