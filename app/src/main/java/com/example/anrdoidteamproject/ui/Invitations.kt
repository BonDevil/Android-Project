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
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
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
import kotlinx.coroutines.CompletableDeferred


@Composable
fun InviteCard(
    inviteInfo: User
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row() {
                Text(
                    text = inviteInfo.firstName,
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = FontFamily(
                        Font(R.font.century_gothic)
                    )
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = inviteInfo.lastName,
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = FontFamily(
                        Font(R.font.century_gothic)
                    )
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
            PromptButton(
                label = R.string.akceptuj,
                onClick = {}
            )
        }
        Divider(color = Color.White, thickness = 2.dp)
    }
}


//@Composable
//fun showAllInvites(invites: List<User>) {
//    LazyColumn {
//        invites.map { item { InviteCard(it) } }
//    }
//    Log.d("eo", "showAllInvites: $invites")
//
//}

@Composable
fun InvitationsList(
    userInfoButtonOnClick: () -> Unit = {},
    homeButtonOnClick: () -> Unit = {},
    settingsButtonOnClick: () -> Unit = {},

    ) {
    var expanded by remember { mutableStateOf(false) }
    var friendsInvites = remember { mutableListOf<Int>() }
    var friendInvitesInfo = remember { mutableListOf<User>() }
    val currentUserHashedEmail = Firebase.auth.currentUser?.email.hashCode()
    val myRef =
        DatabaseConnection.db.getReference("Users/$currentUserHashedEmail/friendInvites")
    var isDataRetrieved by remember { mutableStateOf(false) }
    var isFriendListRetrieved by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            bottomBar(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        },
        topBar = { topBar(message = stringResource(R.string.znajomi)) },
        modifier = Modifier.background(color = Color(0xff181f36))

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color(0xff181f36))
        ) {
            myRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.value != null) {
                        friendsInvites = (dataSnapshot.value as List<Int>).toMutableList()
                        Log.d("eo", "friend invites: $friendsInvites")
                        isDataRetrieved = true
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })

            if (isDataRetrieved) {
                for (hashedFriendMail: Int in friendsInvites) {
                    val friendRef = DatabaseConnection.db.getReference("Users/$hashedFriendMail")
                    friendRef.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            val myUser = dataSnapshot.getValue(User::class.java)
                            if (myUser != null) {
                                friendInvitesInfo =
                                    (friendInvitesInfo + listOf(myUser)) as MutableList<User>
                            }
                            Log.d("eo", "hashedFriendMail loop: $friendInvitesInfo")
                            isFriendListRetrieved = true
                        }

                        override fun onCancelled(error: DatabaseError) {
                            TODO("Not yet implemented")
                        }
                    })
                }
                if (isFriendListRetrieved) {
                    LazyColumn {
                        Log.d("eo", "lazyColumn: $friendInvitesInfo")
                        friendInvitesInfo.map { item { InviteCard(it) } }
                    }
                }
            }

        }
    }
}


@Preview(heightDp = 1000)
@Composable
fun InvitationsListPreview() {
    InvitationsList()
}













