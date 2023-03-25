package com.example.shopping_list_app.data.repository

import androidx.lifecycle.LiveData
import androidx.room.Query
import androidx.room.Update
import com.example.shopping_list_app.data.db.history.HistoryDao
import com.example.shopping_list_app.data.db.history.HistoryItem
import com.example.shopping_list_app.data.db.item.Item
import com.example.shopping_list_app.data.db.item.ItemDao
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class Repository@Inject constructor(private val itemDao: ItemDao,private val historyDao: HistoryDao) {


    fun getAllItems(): LiveData<List<Item>> {
        return itemDao.getAllItems()
    }

    suspend fun insertItem(item: Item){
        return itemDao.insertData(item)
    }

    suspend fun deleteItem(item: Item){
        return itemDao.delete(item)
    }

    suspend fun deleleAllItems(){
        return itemDao.deleteAll()
    }



    fun getAllHistoryItems():LiveData<List<HistoryItem>>{
        return historyDao.getAllHistoryItems()
    }

    suspend fun insertHistoryItem(historyItem: HistoryItem){
        return historyDao.insertHistoryItem(historyItem)
    }

    suspend fun deleteHistoryItem(historyItem: HistoryItem){
        return historyDao.deleteHistoryItem(historyItem)
    }
    suspend fun deleteAllHistoryItems(){
        return historyDao.deleteAllHistoryItem()
    }








}