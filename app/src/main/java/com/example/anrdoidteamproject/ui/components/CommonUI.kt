package com.example.anrdoidteamproject.ui.theme

import androidx.annotation.StringRes
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
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
fun SimpleTextField(
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    TextField(
        value = text,
        onValueChange = { newText ->
            text = newText
        },
        textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
        keyboardOptions = keyboardOptions,
    )
}

@Composable
fun TextFieldWithLabel(
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    @StringRes label: Int
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    TextField(
        label = {
            Text(
                stringResource(label),
                color = Color.White,
            )
        },
        value = text,
        onValueChange = { newText ->
            text = newText
        },
        keyboardOptions = keyboardOptions,
        textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
    )
}

@Composable
fun PasswordTextField(
    keyboardOptions: KeyboardOptions,
    @StringRes label: Int
) {
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
            textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
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
            label = {
                Text(
                    stringResource(label),
                    color = Color.White,
                )
            },
            visualTransformation = if (passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation()
        )
    }
}


@Composable
fun PromptButton(
    @StringRes label: Int,
    ) {
    OutlinedButton(
        onClick = { /*TODO*/ },
        Modifier.width(250.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(26, 51, 101),
            disabledBackgroundColor = Color(70, 99, 255),
        )
    ) {
        Text(
            text = stringResource(label),
            fontSize = 30.sp,
            color = Color.White
        )
    }
}