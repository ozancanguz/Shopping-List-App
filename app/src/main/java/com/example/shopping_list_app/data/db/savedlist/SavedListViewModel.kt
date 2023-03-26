package com.example.shopping_list_app.data.db.savedlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.shopping_list_app.data.repository.Repository
import kotlinx.coroutines.launch

class SavedListViewModel(private val repository: Repository,application: Application) : AndroidViewModel(application) {
    val allSavedLists: LiveData<List<SavedList>> = repository.allSavedLists

    fun insert(savedList: SavedList) = viewModelScope.launch {
        repository.insert(savedList)
    }
}