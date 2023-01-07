package com.example.anrdoidteamproject.ui

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anrdoidteamproject.R
import com.example.anrdoidteamproject.businessLogic.DatabaseConnection
import com.example.anrdoidteamproject.ui.theme.PromptButton
import com.example.anrdoidteamproject.ui.theme.bottomBar
import com.example.anrdoidteamproject.ui.theme.topBar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

@Composable
fun UserInfo(
    userInfoButtonOnClick: () -> Unit = {},
    homeButtonOnClick: () -> Unit = {},
    settingsButtonOnClick: () -> Unit = {},
    friendButtonOnClick: () -> Unit = {}
) {
    val firebaseUser = Firebase.auth.currentUser
    val hashedMail = firebaseUser?.email.hashCode()

    Scaffold(
        bottomBar = {
            bottomBar(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
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
                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    style = TextStyle(color = Color.White, fontSize = 24.sp),
                    text = stringResource(id = R.string.email),
                )
                Text(
                    style = TextStyle(color = Color.White, fontSize = 24.sp),
                    text = firebaseUser?.email.toString()
                )

                Text(
                    style = TextStyle(color = Color.White, fontSize = 24.sp),
                    text = stringResource(id = R.string.telefon),
                )
                Text(
                    style = TextStyle(color = Color.White, fontSize = 24.sp),
                    text = DatabaseConnection.db.getReference("Users/$hashedMail")
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
