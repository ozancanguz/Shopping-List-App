package com.example.shopping_list_app.data.db.item

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shopping_list_app.data.db.history.HistoryDao
import com.example.shopping_list_app.data.db.history.HistoryItem
import com.example.shopping_list_app.data.db.savedlist.SavedList
import com.example.shopping_list_app.data.db.savedlist.SavedListDao

@Database(entities = [Item::class, HistoryItem::class,SavedList::class], version = 1,
    exportSchema = false,)

@TypeConverters(TypeConverter::class)
abstract class ItemListDatabase: RoomDatabase(){
    abstract fun itemDao():ItemDao
    abstract fun historyDao(): HistoryDao

    abstract fun savedListDao():SavedListDao
}


