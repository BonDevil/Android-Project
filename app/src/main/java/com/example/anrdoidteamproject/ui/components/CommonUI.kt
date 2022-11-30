package com.example.anrdoidteamproject.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
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
fun SimpleTextField(keyboardOptions: KeyboardOptions) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    TextField(
        value = text,
        onValueChange = { newText ->
            text = newText
        },
        textStyle = TextStyle(color = Color.White, fontSize = 30.sp),
        keyboardOptions = keyboardOptions
    )
}

@Composable
fun PasswordTextField(keyboardOptions: KeyboardOptions) {
    Column(
    ) {
        var password by rememberSaveable { mutableStateOf("") }
        var passwordVisibility by remember { mutableStateOf(false) }

        val icon = if (passwordVisibility)
            painterResource(id = R.drawable.design_ic_visibility)
        else
            painterResource(id = R.drawable.design_ic_visibility_off)
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            textStyle = TextStyle(color = Color.White, fontSize = 30.sp),
            placeholder = { Text(text = "Password", color = Color.White, fontSize = 30.sp) },

            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    Icon(

                        painter = icon,
                        contentDescription = "Visibility Icon"

                    )
                }
            },
            keyboardOptions = keyboardOptions,
            visualTransformation = if (passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation()

        )
    }
}