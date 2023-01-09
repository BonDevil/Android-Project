package com.example.anrdoidteamproject.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.anrdoidteamproject.AppScreens
import com.example.anrdoidteamproject.R
import com.example.anrdoidteamproject.businessLogic.DatabaseConnection
import com.example.anrdoidteamproject.businessLogic.User
import com.example.anrdoidteamproject.ui.theme.PromptButton
import com.example.anrdoidteamproject.ui.theme.bottomBar
import com.example.anrdoidteamproject.ui.theme.topBar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

private var emailAddress: String = ""
private var firstName: String = ""
private var lastName: String = ""
private var phoneNumber: String = ""

@Composable
fun UserInfo(
    userInfoButtonOnClick: () -> Unit = {},
    homeButtonOnClick: () -> Unit = {},
    settingsButtonOnClick: () -> Unit = {},
    friendButtonOnClick: () -> Unit = {},
    navController: NavController = rememberNavController()
) {

    val firebaseUser = Firebase.auth.currentUser
    val hashedMail = firebaseUser?.email.hashCode()
    val myRef = DatabaseConnection.db.getReference("Users/$hashedMail")
    emailAddress = firebaseUser?.email.toString()

    myRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val myUser = dataSnapshot.getValue(User::class.java)
            Log.d("eo", "$dataSnapshot")
            if (myUser != null) {
                phoneNumber = myUser!!.phoneNumber
                lastName = myUser!!.lastName
                firstName = myUser!!.firstName
            } else
                Log.d("eo", "user is null")
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

    })
    Scaffold(
        bottomBar = {
            bottomBar(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick,
            )
        },
        topBar = { topBar(message = stringResource(R.string.konto)) },
        modifier = Modifier.background(color = Color(0xff181f36))

    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.925f)
                .background(color = Color(0xff181f36))
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .background(Color(24, 31, 54))
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            )
            {
//              show email
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    style = TextStyle(color = Color.White, fontSize = 24.sp),
                    text = stringResource(id = R.string.email),
                )
                Text(
                    style = TextStyle(color = Color.White, fontSize = 24.sp),
                    text = emailAddress
                )

//              show first name
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    style = TextStyle(color = Color.White, fontSize = 24.sp),
                    text = stringResource(id = R.string.imie),
                )
                Text(
                    style = TextStyle(color = Color.White, fontSize = 24.sp),
                    text = firstName
                )

//              show last name
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    style = TextStyle(color = Color.White, fontSize = 24.sp),
                    text = stringResource(id = R.string.nazwisko),
                )
                Text(
                    style = TextStyle(color = Color.White, fontSize = 24.sp),
                    text = lastName,
                )

//              show phone number
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    style = TextStyle(color = Color.White, fontSize = 24.sp),
                    text = stringResource(id = R.string.telefon),
                )
                Text(
                    style = TextStyle(color = Color.White, fontSize = 24.sp),
                    text = phoneNumber,
                )
                Spacer(modifier = Modifier.height(15.dp))
                PromptButton(
                    label = R.string.wylogowanie,
                    onClick = {
                        navController.navigate(AppScreens.LogIn.name)
                    }
                )
                Spacer(modifier = Modifier.height(15.dp))
                PromptButton(
                    label = R.string.znajomi,
                    onClick = {
                        navController.navigate(AppScreens.FriendsList.name)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun UserInfoPreview() {
    UserInfo()
}
