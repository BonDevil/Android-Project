package com.example.anrdoidteamproject.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anrdoidteamproject.R


@Composable
fun topBar(message: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .background(Color(44, 57, 100))
            .fillMaxHeight(0.075f)
            .fillMaxWidth()
    ) {
        Text(
            text = message,
//                zmienic fonta na ten century gothic, cos nie wyszukuje
//                chyba działa juz ale nie wiem czy poprawnie
            fontFamily = FontCentury,
            fontSize = 40.sp,
            fontWeight = FontWeight(750),
            color = Color.White
        )

    }
}

@Composable
fun bottomBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .background(Color(44, 57, 100))
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_user),
            contentDescription = "fi-rr-user",
            modifier = Modifier
                .width(width = 32.dp)
                .height(height = 32.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.img_home),
            contentDescription = "fi-rr-home",
            modifier = Modifier
                .width(width = 32.dp)
                .height(height = 32.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.img_settings),
            contentDescription = "fi-rr-settings",
            modifier = Modifier
                .width(width = 32.dp)
                .height(height = 32.dp)
        )
    }
}

@Composable
fun mainScreen() {
    Column() {
        topBar(message = "ZNAJOMI")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.925f)
                .background(color = Color(0xff181f36))){

        }
        bottomBar()
    }
}

@Composable
fun SimpleTextField() {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    TextField(
        value = text,
        onValueChange = { newText ->
            text = newText
        },

        textStyle = TextStyle(color = Color.White, fontSize = 30.sp)
    )
}