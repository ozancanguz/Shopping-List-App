package com.example.shopping_list_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
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
    fun insertData(item: Item){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertItem(item)
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




}