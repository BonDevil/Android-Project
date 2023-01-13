package com.example.anrdoidteamproject.businessLogic

import androidx.compose.runtime.mutableStateListOf
import com.google.firebase.database.FirebaseDatabase

class DatabaseConnection {
    companion object {
        val db =
            FirebaseDatabase.getInstance("https://androidteamproject-37498-default-rtdb.europe-west1.firebasedatabase.app/")
        var friendList = mutableStateListOf<User>()
    }
}