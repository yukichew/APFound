package com.example.apfound.screens.history

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apfound.model.Item
import com.example.apfound.model.User
import com.example.apfound.model.User.Companion.getCurrentUserId
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MyPostViewModel(): ViewModel() {

  val itemList = MutableStateFlow<List<Item>>(emptyList())

  private val _currentUser = MutableStateFlow(User())
  private val currentUser: StateFlow<User>
    get() = _currentUser

  var name by mutableStateOf("")
  private var userId by mutableStateOf("")

  init {
    viewModelScope.launch {
      if (!getCurrentUserId().isNullOrEmpty()) {
        _currentUser.value = getCurrentUserId()?.let { User.getCurrentUser(it) }!!
        name = currentUser.value.name
        userId = currentUser.value.userId
      }
      refreshMyPostedItems()
    }
  }

  private suspend fun refreshMyPostedItems() {
    val items = if (userId != null) {
      Item.getPosts("userId", userId)
    } else {
      emptyList()
    }
    itemList.value = items
  }
}
