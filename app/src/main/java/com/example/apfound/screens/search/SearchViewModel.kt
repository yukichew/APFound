package com.example.apfound.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apfound.model.Item
import com.example.apfound.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
  val searchResult = MutableStateFlow<List<Item>>(emptyList())
  var keyword by mutableStateOf("")
  val category = savedStateHandle.get<String>("category").orEmpty()

  private val _currentUser = MutableStateFlow(User())
  private val currentUser: StateFlow<User>
    get() = _currentUser

  var name by mutableStateOf("")

  init {
    viewModelScope.launch {
      if (!User.getCurrentUserId().isNullOrEmpty()) {
        _currentUser.value = User.getCurrentUserId()?.let { User.getCurrentUser(it) }!!
        name = currentUser.value.name
      }
      performSearch("Lost Item")
    }
  }

  suspend fun performSearch(filterCriteria: String) {
    val items = Item.getPosts("category", filterCriteria)
    searchResult.value = items
  }

  suspend fun performSearchbyKeyword() {
    val items = Item.getPostsByKeyword(keyword.toLowerCase())
    searchResult.value = items
  }
}
