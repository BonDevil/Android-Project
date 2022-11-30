package com.example.anrdoidteamproject.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anrdoidteamproject.R
import com.example.anrdoidteamproject.ui.theme.topBar


@Composable
fun LogInList() {

    Column(
        modifier = Modifier
            .padding(20.dp)
            .background(Color(24, 31, 54))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

        )
    {
        Image(
            painter = painterResource(R.drawable.img_user), contentDescription = "user",

            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .border(10.dp, color = Color.White, CircleShape)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.email),
            color = Color.White,
            fontSize = 30.sp,
        )
        Spacer(modifier = Modifier.height(15.dp))
        SimpleTextField()
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.haslo),
            color = Color.White,
            fontSize = 30.sp,
        )
        Spacer(modifier = Modifier.height(15.dp))
        RegisterPasswordTextField()

        Spacer(modifier = Modifier.height(60.dp))
        OutlinedButton(
            onClick = { /*TODO*/ }, Modifier.width(250.dp), colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(89, 128, 255),
                disabledBackgroundColor = Color(70, 99, 255),
            )
        ) {
            Text(
                text = stringResource(R.string.logwanie_zacheta),
                fontSize = 30.sp
            )
        }

        Spacer(modifier = Modifier.height(60.dp))
        OutlinedButton(
            onClick = { /*TODO*/ }, Modifier.width(250.dp), colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(89, 128, 255),
                disabledBackgroundColor = Color(70, 99, 255),
            )
        ) {
            Text(
                text = stringResource(R.string.rejestracja_zacheta),
                fontSize = 30.sp
            )
        }
    }
}


@Composable
fun LogInPasswordTextField() {
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
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation()

        )
    }
}

@Composable
fun LogIn() {
    Column() {
        topBar(message = stringResource(R.string.logowanie))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f)
                .background(color = Color(0xff181f36))
        ) {
            LogInList()
        }
    }
}

@Preview
@Composable
fun LogInPreview(){
    LogIn()
}
