package com.example.anrdoidteamproject.businessLogic

import android.util.Log
import androidx.compose.runtime.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class DatabaseConnection {
    companion object {
        val db =
            FirebaseDatabase.getInstance("https://androidteamproject-37498-default-rtdb.europe-west1.firebasedatabase.app/")
        var friendList = mutableStateListOf<User>()
    }

    @Composable
    fun loadFriends() {
        var friends = remember { mutableMapOf<String, String>() }
        val currentUserHashedEmail = Firebase.auth.currentUser?.email.hashCode()
        val friendsRef =
            DatabaseConnection.db.getReference("Users/$currentUserHashedEmail/friends")
        var isLoading by remember { mutableStateOf(true) }
        var isFriendsLoaded by remember { mutableStateOf(false) }
        var friendsAsUsers = remember { mutableListOf<User>() }
        friendList.clear()

        friendsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.value != null) {
                    friends = dataSnapshot.value as HashMap<String, String>
                    for (userHashedMail in friends.keys) {
                        Log.d("essa", "$userHashedMail")
                        val myRef = DatabaseConnection.db.getReference("Users/$userHashedMail")
                        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                DatabaseConnection.friendList.add(dataSnapshot.getValue(User::class.java)!!)
                                Log.d("eo", "friends as users1:$friendsAsUsers")
                                if (friends.keys.last() == userHashedMail)
                                    isFriendsLoaded = true
                            }

                            override fun onCancelled(error: DatabaseError) {
                                TODO("Not yet implemented")
                            }
                        })
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}