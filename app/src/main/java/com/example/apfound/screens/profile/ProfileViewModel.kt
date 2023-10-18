package com.example.apfound.screens.profile

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apfound.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ProfileViewModel(): ViewModel() {

  private val _currentUser = MutableStateFlow(User())
  private val currentUser: StateFlow<User>
    get() = _currentUser

  var contactNumber by mutableStateOf("")
  var gender by mutableStateOf("")
  var name by mutableStateOf("")

  var password by mutableStateOf("")
  var confirmPassword by mutableStateOf("")

  //VM Initialised
  init {
    viewModelScope.launch {
      if (!User.getCurrentUserId().isNullOrEmpty()) {
        _currentUser.value = User.getCurrentUserId()?.let { User.getCurrentUser(it) }!!

        name = currentUser.value.name
        contactNumber = currentUser.value.contactNumber
        gender = currentUser.value.gender
      }
    }
  }

  suspend fun updateProfile(): Boolean {
    val currentUser = User.getCurrentUserId()?.let { User.getCurrentUser(it) }

    return if (currentUser != null) {
      currentUser.name = name
      currentUser.gender = gender
      currentUser.contactNumber = contactNumber
      User.updateUserProfile(currentUser)

    } else {
      false
    }
  }

  // Change password
  suspend fun changePassword(): Boolean {
    return try {
      Firebase.auth.currentUser?.updatePassword(password)?.await()
      true
    } catch (e: Exception) {
      Log.e("ChangePassword", e.message ?: "Password change failed")
      false
    }
  }
}
