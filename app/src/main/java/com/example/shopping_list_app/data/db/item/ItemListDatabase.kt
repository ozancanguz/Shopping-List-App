package com.example.shopping_list_app.data.db.item

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Item::class], version = 1,
    exportSchema = false,)

@TypeConverters(TypeConverter::class)
abstract class ItemListDatabase: RoomDatabase(){
    abstract fun itemDao():ItemDao
}
