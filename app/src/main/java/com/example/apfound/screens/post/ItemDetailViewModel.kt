package com.example.apfound.screens.post

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apfound.model.Item
import com.example.apfound.model.User
import kotlinx.coroutines.launch

class ItemDetailViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

  val itemId = savedStateHandle.get<String>("itemId").orEmpty()

  var name by mutableStateOf("")
  var category by mutableStateOf("")
  var desc by mutableStateOf("")
  var userId by mutableStateOf("")
  var date by mutableStateOf("")
  var image by mutableStateOf<Uri?>(null)

  var fullName by mutableStateOf("")
  var contactNumber by mutableStateOf("")
  var email by mutableStateOf("")

  init {
    viewModelScope.launch {
      if (itemId != "-1" && itemId != "") {
        val record = Item.getPostById(itemId)
        if (record != null) {
          name = record.name
          category = record.category
          desc = record.desc
          userId = record.userId
          date = record.date
          image = Uri.parse(record.image)
        }
      }

      val user = User.getCurrentUser(userId)
      if (user != null) {
        fullName = user.name
        contactNumber = user.contactNumber
        email = user.email
      }

    }
  }

  suspend fun updateItemDetail():Boolean {
    var post = Item.getPostById(itemId)

    return if(post != null) {
      post.name = name
      post.category = category
      post.desc = desc
      image.also { post.image = it.toString() }
      Item.updateItem(post)

    } else {
      false
    }
  }
}
