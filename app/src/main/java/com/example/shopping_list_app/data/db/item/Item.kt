package com.example.shopping_list_app.data.db.item

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "itemlist_table")
data class Item(

    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var itemName:String,
    var amount:Int

)
