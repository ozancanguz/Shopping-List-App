package com.example.shopping_list_app.data.db.savedlist

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.shopping_list_app.data.db.item.Item
import com.example.shopping_list_app.data.db.item.TypeConverter

@Entity(tableName = "saved_lists")
@TypeConverters(TypeConverter::class)
data class SavedList(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val time: Long,
    val items: List<Item>
)