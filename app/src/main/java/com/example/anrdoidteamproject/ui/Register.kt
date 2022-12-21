package com.example.anrdoidteamproject.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import com.example.anrdoidteamproject.ui.theme.*
import com.google.firebase.auth.FirebaseAuth


@Composable
fun RegisterList() {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var repeatPasswordVisibility by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(20.dp)
            .background(Color(24, 31, 54))
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(15.dp))

//        Text field for first name
        TextField(
            value = firstName,
            onValueChange = { newText ->
                firstName = newText
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
            label = {
                Text(
                    stringResource(R.string.imie),
                    color = Color.White,
                )
            }
        )
        Spacer(modifier = Modifier.height(20.dp))

//        Text field for last name
        TextField(
            value = lastName,
            onValueChange = { newText ->
                lastName = newText
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
            label = {
                Text(
                    stringResource(R.string.nazwisko),
                    color = Color.White,
                )
            }
        )
        Spacer(modifier = Modifier.height(15.dp))

//      text field for email
        TextField(
            value = email,
            onValueChange = { newText ->
                email = newText
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
            label = {
                Text(
                    stringResource(R.string.email),
                    color = Color.White,
                )
            }
        )
        Spacer(modifier = Modifier.height(15.dp))

//        Text field for password
        Column() {
            val icon = if (passwordVisibility)
                painterResource(id = R.drawable.design_ic_visibility)
            else
                painterResource(id = R.drawable.design_ic_visibility_off)
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                trailingIcon = {
                    IconButton(onClick = {
                        passwordVisibility = !passwordVisibility
                    }) {
                        Icon(
                            painter = icon,
                            contentDescription = "Visibility Icon",
                            tint = Color.White,
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                label = {
                    Text(
                        stringResource(R.string.haslo),
                        color = Color.White,
                    )
                },
                visualTransformation =
                if (passwordVisibility) VisualTransformation.None
                else PasswordVisualTransformation()
            )
        }
        Spacer(modifier = Modifier.height(15.dp))

//      Text field for repeat password
        Column() {
            val icon = if (repeatPasswordVisibility)
                painterResource(id = R.drawable.design_ic_visibility)
            else
                painterResource(id = R.drawable.design_ic_visibility_off)
            OutlinedTextField(
                value = repeatPassword,
                onValueChange = {
                    repeatPassword = it
                },
                textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                trailingIcon = {
                    IconButton(onClick = {
                        repeatPasswordVisibility = !repeatPasswordVisibility
                    }) {
                        Icon(
                            painter = icon,
                            contentDescription = "Visibility Icon",
                            tint = Color.White,
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                label = {
                    Text(
                        stringResource(R.string.powtorz_haslo),
                        color = Color.White,
                    )
                },
                visualTransformation =
                if (repeatPasswordVisibility) VisualTransformation.None
                else PasswordVisualTransformation()
            )
        }
        Spacer(modifier = Modifier.height(15.dp))

//      Text field for phone number
        TextField(
            value = phoneNumber,
            onValueChange = { newText ->
                phoneNumber = newText
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done
            ),
            textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
            label = {
                Text(
                    stringResource(R.string.telefon),
                    color = Color.White,
                )
            }
        )

        Spacer(modifier = Modifier.height(60.dp))
        PromptButton(
            label = R.string.rejestracja_zacheta,
            onClick = {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("eo", "The user has successfully registered")
                        } else {
                            Log.d("eo", "The user has failed to register")
                        }
                    }
            }
        )
    }
}


@Composable
fun Register() {
    Scaffold(
        topBar = { topBar(message = stringResource(R.string.rejestracja)) },
        modifier = Modifier
            .background(color = Color(0xff181f36))
            .fillMaxHeight(1f)

    ) {
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


@Preview(widthDp = 400, heightDp = 600)
@Composable
fun RegisterPreview2() {
    Register()

}