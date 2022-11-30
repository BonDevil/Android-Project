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
import com.example.anrdoidteamproject.ui.theme.TextFieldWithLabel
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
        Spacer(modifier = Modifier.height(15.dp))

        TextFieldWithLabel(
            KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            label = R.string.imie
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextFieldWithLabel(
            KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            label = R.string.nazwisko
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextFieldWithLabel(
            KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            label = R.string.email
        )

        Spacer(modifier = Modifier.height(15.dp))

        PasswordTextField(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            label = R.string.haslo
        )

        Spacer(modifier = Modifier.height(15.dp))

        PasswordTextField(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            label = R.string.powtorz_haslo
        )
        Spacer(modifier = Modifier.height(15.dp))

        TextFieldWithLabel(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            label = R.string.telefon,

        )

        Spacer(modifier = Modifier.height(60.dp))
        OutlinedButton(
            onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(89, 128, 255),
                disabledBackgroundColor = Color(70, 99, 255),
            )
        ) {
            Text(
                text = stringResource(R.string.rejestracja_zacheta),
                fontSize = 24.sp,
                color = Color.White
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
