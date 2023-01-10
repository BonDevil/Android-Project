package com.example.anrdoidteamproject.ui.theme

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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
            fontSize = 24.sp,
            fontWeight = FontWeight(750),
            color = Color.White
        )
    }
}

@Composable
fun bottomBar(
    userInfoButtonOnClick: () -> Unit = {},
    homeButtonOnClick: () -> Unit = {},
    settingsButtonOnClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .background(Color(44, 57, 100))
            .fillMaxHeight(0.1f)
            .fillMaxWidth()
    ) {
        IconButton(
            onClick = userInfoButtonOnClick,
            modifier = Modifier
                .width(width = 32.dp)
                .height(height = 32.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.img_user),
                contentDescription = "user info button",
                tint = Color.White,
                modifier = Modifier
                    .width(width = 32.dp)
                    .height(height = 32.dp)
            )
        }
        IconButton(
            onClick = homeButtonOnClick,
            modifier = Modifier
                .width(width = 32.dp)
                .height(height = 32.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.img_home),
                contentDescription = "home button",
                tint = Color.White,
                modifier = Modifier
                    .width(width = 32.dp)
                    .height(height = 32.dp),
            )
        }
        IconButton(
            onClick = settingsButtonOnClick,
            modifier = Modifier
                .width(width = 32.dp)
                .height(height = 32.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.img_settings),
                contentDescription = "settings button",
                tint = Color.White,
                modifier = Modifier
                    .width(width = 32.dp)
                    .height(height = 32.dp)
            )
        }
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
    @StringRes label: Int,
    fieldValue: MutableState<String> = mutableStateOf("")
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
            fieldValue.value = text.text
        },
        keyboardOptions = keyboardOptions,
        textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
    )

}

@Composable
fun PasswordTextField(
    keyboardOptions: KeyboardOptions,
    @StringRes label: Int,
    fieldValue: MutableState<String> = mutableStateOf("")
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
                fieldValue.value = password
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
            keyboardOptions = keyboardOptions,
            label = {
                Text(
                    stringResource(label),
                    color = Color.White,
                )
            },
            visualTransformation =
            if (passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation()
        )
    }
}


@Composable
fun PromptButton(
    @StringRes label: Int,
    onClick: () -> Unit = {}
) {
    OutlinedButton(
        onClick = onClick,
        Modifier.width(250.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(26, 51, 101),
            disabledBackgroundColor = Color(70, 99, 255),
        )
    ) {
        Text(
            text = stringResource(label),
            fontSize = 24.sp,
            color = Color.White
        )
    }
}

@Composable
fun topBar21(message: String, message2: String, onClick: () -> Unit = {}) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .background(Color(44, 57, 100))
            .fillMaxHeight(0.075f)
            .fillMaxWidth()
    ) {
        OutlinedButton(
            onClick = onClick,
            Modifier.width(250.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(26, 51, 101),
                disabledBackgroundColor = Color(70, 99, 255),
            )
        ) {
            Text(
                text = message,
                fontFamily = FontCentury,
                fontSize = 40.sp,
                fontWeight = FontWeight(750),
                color = Color.White,
            )
        }
        Text(
            text = message2,
            fontFamily = FontCentury,
            fontSize = 40.sp,
            fontWeight = FontWeight(750),
            color = Color.White
        )

    }
}

@Composable
fun topBar22(message: String, message2: String, onClick: () -> Unit = {}) {

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
            fontFamily = FontCentury,
            fontSize = 40.sp,
            fontWeight = FontWeight(750),
            color = Color.White,
        )
        OutlinedButton(
            onClick = onClick,
            Modifier.width(250.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(26, 51, 101),
                disabledBackgroundColor = Color(70, 99, 255),
            )
        ) {
            Text(
                text = message2,
                fontFamily = FontCentury,
                fontSize = 40.sp,
                fontWeight = FontWeight(750),
                color = Color.White
            )

        }
    }}



    @Composable
    fun Actionbuton2(
        onClick: () -> Unit,
        onClick1: () -> Unit,
        onClick2: () -> Unit,

        @DrawableRes drawable: Int,
        drawable2: ImageVector,
    ) {

        Column {


            FloatingActionButton(
                onClick = onClick1,
                backgroundColor = Color.White
            ) {
                Icon(
                    painterResource(id = drawable),
                    tint = Color(0xFF5980FF),
                    contentDescription = null,
                )
            }

            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )

            FloatingActionButton(
                onClick = onClick2,
                backgroundColor = Color.White
            ) {
                Icon(
                    drawable2,
                    tint = Color(0xFF5980FF),
                    contentDescription = null,
                )
            }

            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )

            FloatingActionButton(
                onClick = onClick,
                backgroundColor = Color(0xFF5980FF)
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_action_more_buttons),
                    contentDescription = "more",
                )

            }
        }
    }


    @Composable
    fun ConfirmButton(
        confirmOnClick: () -> Unit = {}
    ) {
        FloatingActionButton(
            onClick = confirmOnClick,
            backgroundColor = Color.White
        ) {
            Icon(
                Icons.Filled.Check,
                tint = Color(0xFF5980FF),
                contentDescription = "confirm",
            )
        }
    }

    @Composable
    fun AddButton(
        confirmOnClick: () -> Unit = {}
    ) {
        FloatingActionButton(
            onClick = confirmOnClick,
            backgroundColor = Color.White
        ) {
            Icon(
                Icons.Filled.Add,
                tint = Color(0xFF5980FF),
                contentDescription = "ADD",
            )
        }
    }


