package com.example.apfound.screens.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apfound.model.Item
import com.example.apfound.model.User.Companion.getCurrentUserId
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class MyPostViewModel(): ViewModel() {

  val itemList = MutableStateFlow<List<Item>>(emptyList())
  val userId = getCurrentUserId()

  init {
    viewModelScope.launch {
      refreshMyPostedItems()
    }
  }

  suspend fun refreshMyPostedItems() {
    val items = if (userId != null) {
      Item.getPosts("userId", userId)
    } else {
      emptyList()
    }
    itemList.value = items
  }

}
