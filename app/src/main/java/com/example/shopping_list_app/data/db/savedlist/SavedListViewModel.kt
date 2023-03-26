package com.example.shopping_list_app.data.db.savedlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.shopping_list_app.data.db.item.Item
import com.example.shopping_list_app.data.repository.Repository
import javax.inject.Inject

class SavedListViewModel@Inject constructor(private val savedListDao: SavedListDao, application: Application) : AndroidViewModel(application) {

    fun saveList(itemList: List<Item>) {
        val savedList = SavedList(
            id = 0, // auto-generated ID
            itemList = itemList,
            timestamp = System.currentTimeMillis()
        )
        savedListDao.insert(savedList)
    }

    fun getSavedLists(): LiveData<List<SavedList>> {
        return savedListDao.getAll()
    }
}
