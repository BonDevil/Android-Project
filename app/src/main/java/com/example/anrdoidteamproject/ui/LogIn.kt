package com.example.anrdoidteamproject.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anrdoidteamproject.R
import com.example.anrdoidteamproject.ui.theme.*


@Composable
fun LogIn(
    registerButtonOnClick: () -> Unit = {},
    logInButtonOnClick: () -> Unit = {}
) {
    Scaffold(
        topBar = { topBar(message = stringResource(R.string.logowanie)) },
        modifier = Modifier.background(color = Color(0xff181f36))

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f)
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
                Image(
                    painter = painterResource(R.drawable.img_user), contentDescription = "user",
                    modifier = Modifier
                        .fillMaxSize(0.2f)
                )
                Spacer(modifier = Modifier.height(20.dp))

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

                Spacer(modifier = Modifier.height(70.dp))
                PromptButton(
                    label = R.string.logwanie_zacheta,
                    onClick = logInButtonOnClick
                )
                Spacer(modifier = Modifier.height(50.dp))
                PromptButton(
                    label = R.string.rejestracja_zacheta,
                    onClick = registerButtonOnClick
                )
            }
        }
    }
}

@Preview
@Composable
fun LogInPreview() {
    LogIn()
}
