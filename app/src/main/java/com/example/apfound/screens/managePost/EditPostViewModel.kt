package com.example.apfound.screens.managePost

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

class EditPostViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

  val itemId = savedStateHandle.get<String>("itemId").orEmpty()

  var name by mutableStateOf("")
  var category by mutableStateOf("")
  var desc by mutableStateOf("")
  var date by mutableStateOf("")
  var image by mutableStateOf<Uri?>(null)

  init {
    viewModelScope.launch {
      if (itemId != "-1" && itemId != "") {
        val record = Item.getPostById(itemId)
        if (record != null) {
          name = record.name
          category = record.category
          desc = record.desc
          date = record.date
          image = Uri.parse(record.image)
        }
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

  suspend fun deletePostedItem(itemId: String): Boolean {
     return Item.detelePost(itemId)
  }

}
