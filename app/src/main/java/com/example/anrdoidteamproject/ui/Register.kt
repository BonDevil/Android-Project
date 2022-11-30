package com.example.anrdoidteamproject.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anrdoidteamproject.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.anrdoidteamproject.ui.theme.PasswordTextField
import com.example.anrdoidteamproject.ui.theme.SimpleTextField
import com.example.anrdoidteamproject.ui.theme.topBar


@Composable
fun RegisterList() {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .background(Color(24, 31, 54))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

        )
    {

        Text(
            text = stringResource(R.string.imie),
            color = Color.White,
            fontSize = 30.sp,
        )
        Spacer(modifier = Modifier.height(15.dp))

        SimpleTextField(
            KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.nazwisko),
            color = Color.White,
            fontSize = 30.sp,
        )

        Spacer(modifier = Modifier.height(15.dp))

        SimpleTextField(
            KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.email),
            color = Color.White,
            fontSize = 30.sp,
        )

        Spacer(modifier = Modifier.height(15.dp))

        SimpleTextField(
            KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.haslo),
            color = Color.White,
            fontSize = 30.sp,
        )

        Spacer(modifier = Modifier.height(15.dp))

        PasswordTextField(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.powtorz_haslo),
            color = Color.White,
            fontSize = 30.sp,
        )
        Spacer(modifier = Modifier.height(15.dp))

        PasswordTextField(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.telefon),
            color = Color.White,
            fontSize = 30.sp,
        )
        Spacer(modifier = Modifier.height(15.dp))

        SimpleTextField()

        Spacer(modifier = Modifier.height(60.dp))

        OutlinedButton(
            onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
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
fun Register() {
    Column() {
        topBar(message = stringResource(R.string.rejestracja))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f)
                .background(color = Color(0xff181f36))
        ) {
            RegisterList()
        }
    }
}

@Preview
@Composable
fun RegisterPreview() {
    Register()
}
