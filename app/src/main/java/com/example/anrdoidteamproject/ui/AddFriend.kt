package com.example.anrdoidteamproject.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anrdoidteamproject.R
import com.example.anrdoidteamproject.ui.theme.SimpleTextField
import com.example.anrdoidteamproject.ui.theme.bottomBar
import com.example.anrdoidteamproject.ui.theme.topBar


@Composable
fun add() {

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
        SimpleTextField()

    }
}

@Composable
fun AddFriend() {
    Column() {
        topBar(message = stringResource(R.string.dodaj_znajomych))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.925f)
                .background(color = Color(0xff181f36))
        ) {
            add()
        }
        bottomBar()
    }
}

@Preview
@Composable
fun AddFriendPreview() {
    AddFriend()
}

