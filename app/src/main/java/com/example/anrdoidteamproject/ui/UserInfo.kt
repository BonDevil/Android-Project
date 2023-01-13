package com.example.anrdoidteamproject.ui

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.anrdoidteamproject.AppScreens
import com.example.anrdoidteamproject.R
import com.example.anrdoidteamproject.businessLogic.DatabaseConnection
import com.example.anrdoidteamproject.businessLogic.User
import com.example.anrdoidteamproject.ui.theme.LoadingAnimation
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
    var isLoading by remember { mutableStateOf(true) }

    myRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            isLoading = false
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

        if (!isLoading) {
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
                            Firebase.auth.signOut()
                            navController.navigate(AppScreens.LogIn.name)

                        }
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    PromptButton(
                        label = R.string.lista_znajomych,
                        onClick = {
                            navController.navigate(AppScreens.FriendsList.name)
                        }
                    )
                }
            }
        } else {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color(0xff181f36)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LoadingAnimation()
                }
            }
        }
    }
}


@Preview
@Composable
fun UserInfoPreview() {
    UserInfo()
}
