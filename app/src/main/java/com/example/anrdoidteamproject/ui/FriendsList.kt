package com.example.anrdoidteamproject.ui

import android.util.Log
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.stringResource

import androidx.compose.ui.unit.dp
import com.example.anrdoidteamproject.R

import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer


import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.anrdoidteamproject.businessLogic.DatabaseConnection
import com.example.anrdoidteamproject.businessLogic.User
import com.example.anrdoidteamproject.ui.theme.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CountDownLatch


data class Osoba(val Imie: String, val Nazwisko: String)

@Composable
fun PersonCard(user: User) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(modifier = Modifier.padding(20.dp)) {
            Text(
                text = user.firstName + " " + user.lastName,
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = FontFamily(
                    Font(R.font.century_gothic)
                )
            )
        }
        Divider(color = Color.White, thickness = 2.dp)

    }

}

@Composable
fun ListFriends(users: List<User>) {
    Log.d("eo", "List friends: $users")
    LazyColumn {
        users.map { item { PersonCard(it) } }
    }
}


@Composable
fun FriendsList(
    userInfoButtonOnClick: () -> Unit = {},
    homeButtonOnClick: () -> Unit = {},
    settingsButtonOnClick: () -> Unit = {},
    addFriends: () -> Unit = {},
    invitationButton: () -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }
    var friends = remember { mutableMapOf<String, String>() }
    val currentUserHashedEmail = Firebase.auth.currentUser?.email.hashCode()
    val friendsRef =
        DatabaseConnection.db.getReference("Users/$currentUserHashedEmail/friends")
    var isLoading by remember { mutableStateOf(true) }
    var isFriendsLoaded by remember { mutableStateOf(false) }
    var friendsAsUsers = remember { mutableListOf<User>() }
    Scaffold(
        bottomBar = {
            bottomBar(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        },
        topBar = { topBar(message = stringResource(R.string.znajomi)) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { expanded = !expanded },
                backgroundColor = Color.White
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_action_more_buttons),
                    tint = Color.Black,
                    contentDescription = "more",
                )
            }
            if (expanded) {
                Actionbuton2(
                    onClick = { expanded = !expanded },
                    onClick1 = addFriends,
                    onClick2 = invitationButton,
                    drawable = R.drawable.img_add_user,
                    drawable2 = Icons.Filled.Notifications
                )
            }
        },
        modifier = Modifier.background(color = Color(0xff181f36))

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color(0xff181f36))
        ) {
            friendsRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    isLoading = false
                    if (dataSnapshot.value != null) {
                        friends = dataSnapshot.value as HashMap<String, String>
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })

            if (!isLoading) {
                DatabaseConnection.friendList.clear()
                for (userHashedMail in friends.keys) {
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
                Log.d("eo", "friends as users2:$friendsAsUsers")
            }
            if (isFriendsLoaded)
                ListFriends(DatabaseConnection.friendList)
        }
    }
}


@Preview(heightDp = 1000)
@Composable
fun FriendsListPreview() {
    FriendsList()
}


//suspend fun getFriends(): List<User> {
//    var friends = mutableMapOf<String, String>()
//    val currentUserHashedEmail = Firebase.auth.currentUser?.email.hashCode()
//    val friendsRef =
//        DatabaseConnection.db.getReference("Users/$currentUserHashedEmail/friends")
//    var isLoading = mutableStateOf(true)
//    var isFriendsLoaded = mutableStateOf(false)
//    var friendsAsUsers = mutableListOf<User>()
//
//
//    friends = friendsRef.get().await() as HashMap<String, String>
//
//    for (userHashedMail in friends.keys) {
//        val currFriendRef = DatabaseConnection.db.getReference("Users/$userHashedMail")
//        friendsAsUsers.add(currFriendRef.get().await().value as User)
//    }
//    Log.d("eo", "friends as users2:$friendsAsUsers")
//    return friendsAsUsers
//}