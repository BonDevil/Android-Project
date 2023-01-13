package com.example.anrdoidteamproject.ui

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anrdoidteamproject.R
import com.example.anrdoidteamproject.businessLogic.DatabaseConnection
import com.example.anrdoidteamproject.ui.theme.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

@Composable
fun AddFriend(
    userInfoButtonOnClick: () -> Unit = {},
    homeButtonOnClick: () -> Unit = {},
    settingsButtonOnClick: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var isDataRetrieved by remember { mutableStateOf(false) }
    var showEmailError by remember { mutableStateOf(false) }

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
            ConfirmButton(confirmOnClick = {
                if (Patterns.EMAIL_ADDRESS.matcher(email.toString()).matches()) {

                    val currentUserEmail = Firebase.auth.currentUser?.email
                    val currentUserHashedEmail = Firebase.auth.currentUser?.email.hashCode()
                    val invitedUserHashedEmail = email.hashCode()
                    val myRef =
                        DatabaseConnection.db.getReference("Users/$invitedUserHashedEmail/friendInvites")

                    myRef.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            if (!currentUserHashedEmail.equals(invitedUserHashedEmail)) {
                                myRef.child(currentUserHashedEmail.toString())
                                    .setValue(currentUserEmail.toString())
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            TODO("Not yet implemented")
                        }
                    })
                } else {
                    showEmailError=true
                }
            })
        },
        modifier = Modifier.background(color = Color(0xff181f36))

    ) {
        if (showEmailError) {
            Toast.makeText(
                LocalContext.current, stringResource(R.string.toastCorectValue),
                Toast.LENGTH_SHORT
            ).show()
            showEmailError = false
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color(0xff181f36))
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .background(Color(24, 31, 54))
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,

                )
            {

                Text(
                    text = stringResource(R.string.podaj_e_mail_itd),
                    color = Color.White,
                    fontSize = 30.sp,
                )
                Spacer(modifier = Modifier.height(15.dp))
                TextField(
                    value = email,
                    onValueChange = { newText ->
                        email = newText
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    ),
                    textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                    label = {
                        Text(
                            stringResource(R.string.email),
                            color = Color.White,
                        )
                    }
                )

            }
        }
    }
}

@Preview
@Composable
fun AddFriendPreview() {
    AddFriend()
}



