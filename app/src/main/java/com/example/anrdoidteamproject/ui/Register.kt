package com.example.anrdoidteamproject.ui

import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.anrdoidteamproject.AppScreens
import com.example.anrdoidteamproject.businessLogic.DatabaseConnection
import com.example.anrdoidteamproject.businessLogic.User
import com.example.anrdoidteamproject.ui.theme.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

@Composable
fun Register(
    navController: NavController = rememberNavController()
) {
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
            var firstName by remember { mutableStateOf("") }
            var lastName by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var repeatPassword by remember { mutableStateOf("") }
            var phoneNumber by remember { mutableStateOf("") }
            var passwordVisibility by remember { mutableStateOf(false) }
            var repeatPasswordVisibility by remember { mutableStateOf(false) }
            val auth by lazy { Firebase.auth }
            var showRegisterError by remember { mutableStateOf(false) }
            var ThePasswordIsTooShort by remember { mutableStateOf(false) }
            var passwordsDoNotMatch by remember { mutableStateOf(false) }

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
                TextField(value = firstName, onValueChange = { newText ->
                    firstName = newText
                }, keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                ), textStyle = TextStyle(color = Color.White, fontSize = 16.sp), label = {
                    Text(
                        stringResource(R.string.imie),
                        color = Color.White,
                    )
                })
                Spacer(modifier = Modifier.height(20.dp))
//        Text field for last name
                TextField(value = lastName, onValueChange = { newText ->
                    lastName = newText
                }, keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                ), textStyle = TextStyle(color = Color.White, fontSize = 16.sp), label = {
                    Text(
                        stringResource(R.string.nazwisko),
                        color = Color.White,
                    )
                })
                Spacer(modifier = Modifier.height(15.dp))
//      text field for email
                TextField(value = email, onValueChange = { newText ->
                    email = newText
                }, keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                ), textStyle = TextStyle(color = Color.White, fontSize = 16.sp), label = {
                    Text(
                        stringResource(R.string.email),
                        color = Color.White,
                    )
                })
                Spacer(modifier = Modifier.height(15.dp))
//        Text field for password
                Column() {
                    val icon =
                        if (passwordVisibility) painterResource(id = R.drawable.design_ic_visibility)
                        else painterResource(id = R.drawable.design_ic_visibility_off)
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
                            keyboardType = KeyboardType.Password, imeAction = ImeAction.Next
                        ),
                        label = {
                            Text(
                                stringResource(R.string.haslo),
                                color = Color.White,
                            )
                        },
                        visualTransformation = if (passwordVisibility) VisualTransformation.None
                        else PasswordVisualTransformation()
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
//      Text field for repeat password
                Column() {
                    val icon =
                        if (repeatPasswordVisibility) painterResource(id = R.drawable.design_ic_visibility)
                        else painterResource(id = R.drawable.design_ic_visibility_off)
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
                            keyboardType = KeyboardType.Password, imeAction = ImeAction.Next
                        ),
                        label = {
                            Text(
                                stringResource(R.string.powtorz_haslo),
                                color = Color.White,
                            )
                        },
                        visualTransformation = if (repeatPasswordVisibility) VisualTransformation.None
                        else PasswordVisualTransformation()
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
//      Text field for phone number
                TextField(value = phoneNumber, onValueChange = { newText ->
                    phoneNumber = newText
                }, keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone, imeAction = ImeAction.Done
                ), textStyle = TextStyle(color = Color.White, fontSize = 16.sp), label = {
                    Text(
                        stringResource(R.string.telefon),
                        color = Color.White,
                    )
                })
                Spacer(modifier = Modifier.height(60.dp))
                PromptButton(label = R.string.rejestracja_zacheta, onClick = {
                    if (password.length >= 6 && password.equals(repeatPassword)) {
                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("eo", "createUserWithEmail:success")
                                    val myUser = User(firstName, lastName, phoneNumber, email)
                                    val reference = DatabaseConnection.db.getReference("Users")
                                    val hashedEmail = email.hashCode().toString()
                                    reference.child(hashedEmail).setValue(myUser)
                                        .addOnCompleteListener {
                                            navController.navigate(AppScreens.UserInfo.name)
                                        }
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("eo", "createUserWithEmail:failure", task.exception)
                                    showRegisterError = true
                                }
                            }
                    } else if (password.length <= 6) {
                        ThePasswordIsTooShort= true

                    } else if (password != repeatPassword) {
                        passwordsDoNotMatch= true
                    }
                })
                Spacer(modifier = Modifier.height(20.dp))
                PromptButton(label = R.string.logowanie_powrot, onClick = {
                    navController.navigate(AppScreens.LogIn.name)
                })
                if (showRegisterError) {
                    Toast.makeText(
                        LocalContext.current, stringResource(R.string.showRegisterError), Toast.LENGTH_SHORT
                    ).show()
                    showRegisterError = false
                }
                if (ThePasswordIsTooShort) {
                    Toast.makeText(
                        LocalContext.current, stringResource(R.string.ThePasswordIsTooShort), Toast.LENGTH_SHORT
                    ).show()
                    ThePasswordIsTooShort = false
                }
                if (passwordsDoNotMatch) {
                    Toast.makeText(
                        LocalContext.current, stringResource(R.string.toastPasswordsDoNotMatch), Toast.LENGTH_SHORT
                    ).show()
                    passwordsDoNotMatch = false
                }
            }
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