package com.example.shopping_list_app.data.db.history

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "historyItems_table")
data class HistoryItem(

    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var itemName:String,
    var amount:Int,
    @ColumnInfo(name = "currentDate")
    val currentDate: String


)
