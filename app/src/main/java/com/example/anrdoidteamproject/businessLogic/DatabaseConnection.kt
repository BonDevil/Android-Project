package com.example.anrdoidteamproject.businessLogic

import com.google.firebase.database.FirebaseDatabase

class DatabaseConnection {
    companion object {
        val db =
            FirebaseDatabase.getInstance("https://androidteamproject-37498-default-rtdb.europe-west1.firebasedatabase.app/")
    }
}