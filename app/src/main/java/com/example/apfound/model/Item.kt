package com.example.apfound.model

import android.net.Uri
import android.os.Parcelable
import android.util.Log
import com.example.apfound.data.DatabaseCollection
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.tasks.await

@Parcelize
data class Item(
  val itemId: String = "",
  val userId: String = "",
  val name: String = "",
  val desc: String = "",
  val category: String = "",
  var image: String? = null,
  val date: String = "",
  val time: String = "",
) : Parcelable {
  companion object {
    private val firestore = Firebase.firestore.collection(DatabaseCollection.ITEM)

    suspend fun updateItem(newItem: Item): Boolean {
      return try {
        firestore.document(newItem.itemId).set(newItem).await()
        true
      } catch (ex: Exception) {
        Log.println(Log.INFO, "APFound", ex.message.orEmpty())
        false
      }
    }

    suspend fun getAllItems(): List<Item> {
      return try {
        val querySnapshot = firestore.get().await()
        if (!querySnapshot.isEmpty) {
          querySnapshot.toObjects()
        } else {
          emptyList()
        }
      } catch (ex: Exception) {
        Log.println(Log.INFO, "APFound", ex.message.orEmpty())
        emptyList()
      }
    }

    suspend fun getPosts(field: String, value: String): List<Item> {
      return try {
        val querySnapshot = firestore.whereEqualTo(field, value).get().await()
        if (!querySnapshot.isEmpty) {
          querySnapshot.toObjects()
        } else {
          emptyList()
        }
      } catch (ex: Exception) {
        Log.e("APFound", "Error fetching my posted items: ${ex.message}")
        emptyList()
      }
    }
  }
}
