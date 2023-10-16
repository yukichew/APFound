package com.example.apfound.screens.signup

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.apfound.model.User
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class SignUpViewModel : ViewModel() {
  var email by mutableStateOf("")
  var password by mutableStateOf("")
  var contactNumber by mutableStateOf("")
  var gender by mutableStateOf("")
  var name by mutableStateOf("")

  private suspend fun createNewUser(): AuthResult? {
    return try {
      val data = Firebase.auth
        .createUserWithEmailAndPassword(email, password)
        .await()
      data
    } catch (e: Exception) {
      Log.println(Log.INFO, "APFound", e.message.orEmpty())
      null
    }
  }

  suspend fun registerUser(): AuthResult? {
    val auth = createNewUser()
    if (auth != null) {
      val user = auth.user
      if (user != null) {
        // Save user data to Firestore
        User.createUser(
          userId = user.uid,
          name = name,
          email = user.email.orEmpty(),
          contactNumber = contactNumber,
          gender = gender
        )

        // update current user in the app
        User.updateLoginUser()
      }
    }
    return auth
  }
}
