package com.example.apfound.model

import android.os.Parcelable
import android.util.Log
import com.example.apfound.data.DatabaseCollection
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.tasks.await

@Parcelize
data class User(
  val userId: String = "",
  var name: String = "",
  var contactNumber: String = "",
  var email: String = "",
  var gender: String = ""
) : Parcelable {
  companion object {
    private val firestore = Firebase.firestore.collection(DatabaseCollection.USER)
    private var currentUserId = Firebase.auth.currentUser?.uid

    fun getCurrentUserId(): String? {
      return currentUserId
    }

    suspend fun createUser(
      userId: String,
      name: String,
      email: String,
      contactNumber: String,
      gender: String
    ) {
      try {
        val data = User(
          userId = userId,
          name = name,
          email = email,
          contactNumber = contactNumber,
          gender = gender
        )
        updateUserProfile(data)

      } catch (ex: Exception) {
        Log.e("APFound", "User creation failed: ${ex.message}")
      }
    }

    fun updateLoginUser() {
      currentUserId = Firebase.auth.currentUser?.uid
    }

    // Get current user info
    suspend fun getCurrentUser(userId: String): User? {
      return try {
        val data = firestore.document(userId).get().await()
        val currentUser: User? = data.toObject<User>()
        currentUser

      } catch (ex: Exception) {
        Log.println(Log.INFO, "APFound", ex.message.orEmpty())
        null
      }
    }

    // Update user profile information in Firestore
    suspend fun updateUserProfile(data: User): Boolean {
      return try {
        firestore.document(data.userId).set(data).await()
        true
      } catch (ex: Exception) {
        Log.println(Log.INFO, "APFound", ex.message.orEmpty())
        false
      }
    }

  }
}
