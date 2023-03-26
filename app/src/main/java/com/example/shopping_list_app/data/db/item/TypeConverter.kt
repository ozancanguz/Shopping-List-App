package com.example.shopping_list_app.data.db.item

import com.example.shopping_list_app.data.db.history.HistoryItem
import com.example.shopping_list_app.data.db.savedlist.SavedList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverter {

    var gson = Gson()

    @androidx.room.TypeConverter
    fun ProductToString(item: Item): String {
        return gson.toJson(item)
    }

    @androidx.room.TypeConverter
    fun stringToProducts(data: String): Item {
        val listType = object : TypeToken<Item>() {}.type
        return gson.fromJson(data, listType)
    }

    @androidx.room.TypeConverter
    fun ProductToStringg(historyItem: HistoryItem): String {
        return gson.toJson(historyItem)
    }

    @androidx.room.TypeConverter
    fun stringToProductss(data: String): HistoryItem {
        val listType = object : TypeToken<Item>() {}.type
        return gson.fromJson(data, listType)
    }

    @androidx.room.TypeConverter
    fun ProductToStringgg(savedList: List<SavedList>): String {
        return gson.toJson(savedList)
    }

    @androidx.room.TypeConverter
    fun stringToProductsss(data: String): List<SavedList> {
        val listType = object : TypeToken<List<SavedList>>() {}.type
        return gson.fromJson(data, listType)
    }




}