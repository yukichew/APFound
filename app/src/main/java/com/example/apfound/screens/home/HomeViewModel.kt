package com.example.apfound.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apfound.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(): ViewModel() {
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
    }
  }
}
