package com.example.anrdoidteamproject.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anrdoidteamproject.R
import com.example.anrdoidteamproject.businessLogic.DatabaseConnection
import com.example.anrdoidteamproject.businessLogic.User
import com.example.anrdoidteamproject.ui.theme.ConfirmButton
import com.example.anrdoidteamproject.ui.theme.bottomBar
import com.example.anrdoidteamproject.ui.theme.topBar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


@Composable
fun PersonCard2(per: Osoba) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(modifier = Modifier.padding(20.dp)) {
            CheckBoxDemo()
            Text(
                text = per.Imie,
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = FontFamily(
                    Font(R.font.century_gothic)
                )
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = per.Nazwisko,
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = FontFamily(
                    Font(R.font.century_gothic)
                )
            )
            Spacer(modifier = Modifier.width(1.dp))

        }
        Divider(color = Color.White, thickness = 2.dp)

    }

}


@Composable
fun listChooseFriends(users: List<User>) {
    LazyColumn {
        users.map { item { PersonCard(it) } }
    }
}

@Composable
fun CheckBoxDemo() {
    val checkedState = rememberSaveable { mutableStateOf(false) }
    Checkbox(
        checked = checkedState.value,
        onCheckedChange = { checkedState.value = it }
    )
}

@Composable
fun ChooseFriends(
    userInfoButtonOnClick: () -> Unit = {},
    homeButtonOnClick: () -> Unit = {},
    settingsButtonOnClick: () -> Unit = {}
) {
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
        topBar = { topBar(message = stringResource(R.string.dodaj_znajomych)) },
        floatingActionButton = {
            ConfirmButton(confirmOnClick = { /*TODO*/ }
            )
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
                for (userHashedMail in friends.keys) {
                    val myRef = DatabaseConnection.db.getReference("Users/$userHashedMail")
                    myRef.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            friendsAsUsers.add(dataSnapshot.getValue(User::class.java)!!)
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
                listChooseFriends(users = friendsAsUsers)
        }
    }
}


@Preview
@Composable
fun ChooseFriendsPreview() {
    ChooseFriends()
}





