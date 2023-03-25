package com.example.shopping_list_app.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.shopping_list_app.data.db.item.Item
import com.example.shopping_list_app.data.repository.Repository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemListViewModel @Inject constructor(private val repository: Repository, application: Application):AndroidViewModel(application){



    //get all data list
    val getAllItems: LiveData<List<Item>>





    // init get all data
    init {
        getAllItems=repository.getAllItems()
    }


    // get all items
    fun getAllItems(){
        repository.getAllItems()
    }

    // insert item
    // insert or update item
    fun insertOrUpdateData(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            // check if the item already exists in the list
            val existingItem = getAllItems.value?.find { it.itemName == item.itemName }
            if (existingItem != null) {
                // if the item already exists, update its amount
                existingItem.amount += item.amount
                repository.insertItem(existingItem)
            } else {
                // if the item doesn't exist, insert it into the database
                repository.insertItem(item)
            }
        }
    }
    // delete single item
    fun deleteItem(item: Item){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteItem(item)
        }
    }

    // delete all
    fun deleteAll(){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleleAllItems()
        }
    }

    val isEmpty: LiveData<Boolean> = Transformations.map(getAllItems) { list ->
        list.isEmpty()
    }


}