package com.example.apfound.screens.addPost

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apfound.model.Item
import com.example.apfound.model.User
import com.example.apfound.model.User.Companion.getCurrentUserId
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.UUID

class PostViewModel() : ViewModel() {
  var name by mutableStateOf("")
  var desc by mutableStateOf("")
  var category by mutableStateOf("")
  var image by mutableStateOf<Uri?>(null)
  private var userId by mutableStateOf("")

  private val storageReference: StorageReference = FirebaseStorage.getInstance().reference

  private val _currentUser = MutableStateFlow(User())
  private val currentUser: StateFlow<User>
    get() = _currentUser

  var username by mutableStateOf("")

  init {
    viewModelScope.launch {
      if (!getCurrentUserId().isNullOrEmpty()) {
        _currentUser.value = getCurrentUserId()?.let { User.getCurrentUser(it) }!!
        username = currentUser.value.name
        userId = currentUser.value.userId
      }
    }
  }

  private fun generateUniqueItemId(): String {
    return UUID.randomUUID().toString()
  }

  private fun getCurrentDateTime(): Pair<String, String> {
    val cal = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    val date = dateFormat.format(cal.time)
    val time = timeFormat.format(cal.time)
    return Pair(date, time)
  }

  private suspend fun uploadImage(image: Uri): Uri? {
    val itemId = generateUniqueItemId()
    val imageFileName = "item_$itemId.jpg"
    val imageStorageRef = storageReference.child("images/$imageFileName")
    val uploadTask = imageStorageRef.putFile(image)

    var url: Uri? = null

    try {
      val downloadUrl = uploadTask.continueWithTask { task ->
        if (!task.isSuccessful) {
          task.exception?.let {
            throw it
          }
        }
        imageStorageRef.downloadUrl

      }.addOnCompleteListener { task ->
        if (task.isSuccessful) {
          val downloadUri = task.result
          if (downloadUri != null) {
            url = downloadUri
          } else {
            throw Exception("Download URL is null")
          }
        } else {
          throw Exception("Task not successful")
        }
      }.await()

      downloadUrl ?: throw Exception("Image upload or download URL retrieval failed")

    } catch (e: Exception) {
      Log.println(Log.INFO, "APFound", e.message.orEmpty())
    }
    return url
  }

  suspend fun createNewItem(): Boolean {
    try {
      val currentDateTime = getCurrentDateTime()
      val imageUrl = image?.let { uploadImage(it) }
      val itemId = generateUniqueItemId()
      if (imageUrl != null) {
        val newItem = Item(
          itemId = itemId,
          userId = userId,
          name = name.toLowerCase(),
          desc = desc,
          category = category,
          image = imageUrl.toString(),
          date = currentDateTime.first,
          time = currentDateTime.second
        )
        return Item.updateItem(newItem)
      }

    } catch (e: Exception) {
      Log.e("APFound", "Item creation failed: ${e.message}")
    }
    return false
  }
}
