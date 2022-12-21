package com.example.anrdoidteamproject.ui

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.anrdoidteamproject.AppScreens
import com.example.anrdoidteamproject.R
import com.example.anrdoidteamproject.ui.theme.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import androidx.compose.material.MaterialTheme
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.runtime.Composable

@SuppressLint("UnrememberedMutableState")
@Composable
fun LogIn(
    registerButtonOnClick: () -> Unit = {},
    logInButtonOnClick: () -> Unit = {},
    navController: NavController = rememberNavController(),
) {

    val auth by lazy { Firebase.auth }
    var password = mutableStateOf("")
    var email = mutableStateOf("")
    var showPasswordError by remember { mutableStateOf(false) }
    Scaffold(
        topBar = { topBar(message = stringResource(R.string.logowanie)) },
        modifier = Modifier.background(color = Color(0xff181f36)),

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
                    label = R.string.email,
                    email
                )
                Spacer(modifier = Modifier.height(15.dp))

                PasswordTextField(
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    label = R.string.haslo,
                    password
                )

                Spacer(modifier = Modifier.height(70.dp))
                PromptButton(
                    label = R.string.logwanie_zacheta,
                    onClick = {
                        if (email.value != "" && password.value != "") {
                            auth.signInWithEmailAndPassword(email.value, password.value)
                                .addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        Log.d("eo", "The user has successfully logged in")
                                        showPasswordError = false
                                        navController.navigate(AppScreens.TripsList.name)
                                    } else {
                                        Log.d("eo", "The user has failed to log in")
                                        showPasswordError = true
                                    }
                                }
                        }
                    }
                )
                if (showPasswordError) {
                    SimpleAlertDialog(
                        onDismiss = { showPasswordError = false }
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
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

@Composable
fun SimpleAlertDialog(
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss)
            { Text(text = "OK") }
        },
        title = { Text(text = "Wrong credentials") },
        text = { Text(text = "Incorrect email address or password") }
    )
}
