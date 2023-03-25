package com.example.shopping_list_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.shopping_list_app.data.db.history.HistoryItem
import com.example.shopping_list_app.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryItemViewModel@Inject constructor(private val repository: Repository, application: Application) :
    AndroidViewModel(application){



    //get all history item  list
    val getAllHistoryItems: LiveData<List<HistoryItem>>

    // init get all data
    init {
        getAllHistoryItems=repository.getAllHistoryItems()
    }


    fun insertHistoryItem(historyItem: HistoryItem){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertHistoryItem(historyItem)
        }
    }

    fun deleteHistoryItem(historyItem: HistoryItem){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteHistoryItem(historyItem)
        }
    }

    fun deleteAllHistoryItems(){
        viewModelScope.launch {
            repository.deleteAllHistoryItems()
        }
    }

    val isEmpty2: LiveData<Boolean> = Transformations.map(getAllHistoryItems) { list ->
        list.isEmpty()
    }
}
