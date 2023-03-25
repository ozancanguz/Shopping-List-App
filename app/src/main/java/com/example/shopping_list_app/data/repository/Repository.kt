package com.example.shopping_list_app.data.repository

import androidx.lifecycle.LiveData
import com.example.shopping_list_app.data.db.item.Item
import com.example.shopping_list_app.data.db.item.ItemDao
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class Repository@Inject constructor(private val itemDao: ItemDao) {


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


}