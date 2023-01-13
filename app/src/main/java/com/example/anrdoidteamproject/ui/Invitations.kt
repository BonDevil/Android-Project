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
import com.example.anrdoidteamproject.ui.theme.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase


@Composable
fun InviteCard(
    inviteInfo: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = inviteInfo,
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = FontFamily(
                    Font(R.font.century_gothic)
                )
            )

            Spacer(modifier = Modifier.width(5.dp))
            PromptButton(
                label = R.string.akceptuj,
                onClick = {
                    val currentUserEmail = Firebase.auth.currentUser?.email
                    val currentUserHashedEmail = Firebase.auth.currentUser?.email.hashCode()
                    val invitingUserHashedEmail = inviteInfo.hashCode()
                    val currUserRef =
                        DatabaseConnection.db.getReference("Users/$currentUserHashedEmail/friends")

                    val invitingUserRef =
                        DatabaseConnection.db.getReference("Users/$invitingUserHashedEmail/friends")

                    val deleteFriendInviteRef =
                        DatabaseConnection.db.getReference("Users/$currentUserHashedEmail/friendInvites")

                    currUserRef.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            currUserRef.child(invitingUserHashedEmail.toString()).setValue(true)
                            invitingUserRef.child(currentUserHashedEmail.toString()).setValue(true)
                            deleteFriendInviteRef.child("$invitingUserHashedEmail").removeValue()
                        }

                        override fun onCancelled(error: DatabaseError) {
                            TODO("Not yet implemented")
                        }
                    })
                }
            )
        }
        Divider(color = Color.White, thickness = 2.dp)
    }
}


@Composable
fun showAllInvites(invites: MutableCollection<String>) {
    LazyColumn {
        invites.map { item { InviteCard(it) } }
    }
    Log.d("eo", "showAllInvites: $invites")
}

@Composable
fun InvitationsList(
    userInfoButtonOnClick: () -> Unit = {},
    homeButtonOnClick: () -> Unit = {},
    settingsButtonOnClick: () -> Unit = {},

    ) {
    var deletedFriend by remember { mutableStateOf(false) }
    var friendsInvites = remember { mutableMapOf<String, String>() }
    var isLoading by remember { mutableStateOf(true) }
    val currentUserHashedEmail = Firebase.auth.currentUser?.email.hashCode()
    val myRef =
        DatabaseConnection.db.getReference("Users/$currentUserHashedEmail/friendInvites")

    Scaffold(
        bottomBar = {
            bottomBar(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        },
        topBar = { topBar(message = stringResource(R.string.zaproszenia)) },
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
                    isLoading = false
                    if (dataSnapshot.value != null) {
                        friendsInvites = dataSnapshot.value as HashMap<String, String>
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
            if (!isLoading) {
                showAllInvites(invites = friendsInvites.values)
            }
        }
    }
}


@Preview(heightDp = 1000)
@Composable
fun InvitationsListPreview() {
    InvitationsList()
}













