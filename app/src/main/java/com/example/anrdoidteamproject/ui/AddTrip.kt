package com.example.anrdoidteamproject.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anrdoidteamproject.R
import com.example.anrdoidteamproject.ui.theme.Actionbuton2
import com.example.anrdoidteamproject.ui.theme.SimpleTextField
import com.example.anrdoidteamproject.ui.theme.bottomBar
import com.example.anrdoidteamproject.ui.theme.topBar

@Composable
fun trip_valus() {

    Column(
        modifier = Modifier

            .background(Color(24, 31, 54))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

        )
    {
        Column(
            modifier = Modifier
                .padding(40.dp)
                .background(Color(24, 31, 54))
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,

            ) {
            Text(
                text = stringResource(R.string.nazwa),
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily(
                    Font(R.font.century_gothic)
                ),
            )
            Spacer(modifier = Modifier.height(15.dp))
            TextField(
                value = "",
                onValueChange = {},
//            KeyboardOptions(
//                keyboardType = KeyboardType.Text,
//                imeAction = ImeAction.Next
//            ),
                modifier = Modifier
                    .border(2.dp, Color(89, 128, 255), RoundedCornerShape(10))

                    .background(Color(217, 217, 217), RoundedCornerShape(10))
                    .heightIn(min = 56.dp),

                )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(R.string.opis),
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily(
                    Font(R.font.century_gothic)
                ),
            )
            Spacer(modifier = Modifier.height(15.dp))
            TextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .border(2.dp, Color(89, 128, 255), RoundedCornerShape(10))
                    .background(Color(217, 217, 217), RoundedCornerShape(10))
                    .heightIn(min = 56.dp),
//
//            KeyboardOptions(
//                keyboardType = KeyboardType.Text,
//                imeAction = ImeAction.Next
//            ),
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(R.string.planowana_kwota),
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily(
                    Font(R.font.century_gothic)
                ),
            )
            Spacer(modifier = Modifier.height(15.dp))
            TextField(
                value = "",
                onValueChange = {},
//            KeyboardOptions(
//                keyboardType = KeyboardType.Text,
//                imeAction = ImeAction.Next
//            ),
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .border(2.dp, Color(89, 128, 255), RoundedCornerShape(10))
                    .background(Color(217, 217, 217), RoundedCornerShape(10))
                    .heightIn(min = 56.dp),

                )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(R.string.ilosc_dni),
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily(
                    Font(R.font.century_gothic)
                ),
            )
            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                value = "",
                onValueChange = {},
//            KeyboardOptions(
//                keyboardType = KeyboardType.Text,
//                imeAction = ImeAction.Next
//            ),
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .border(2.dp, Color(89, 128, 255), RoundedCornerShape(10))
                    .background(Color(217, 217, 217), RoundedCornerShape(10))
                    .heightIn(min = 56.dp),


                )
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Composable
fun AddTrip(
    userInfoButtonOnClick: () -> Unit = {},
    homeButtonOnClick: () -> Unit = {},
    settingsButtonOnClick: () -> Unit = {},
    addFriendsToTrip: () -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    Scaffold(
        bottomBar = {
            bottomBar(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        },
        topBar = { topBar(message = stringResource(R.string.dodaj_wycieczke)) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { expanded = !expanded },
                backgroundColor = Color.White
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_action_more_buttons),
                    tint = Color.Black,
                    contentDescription = "more",
                )
            }

            if (expanded) {
                Actionbuton2(
                    onClick = { expanded = !expanded },
                    onClick1 = addFriendsToTrip,
                    onClick2 = {/*TODO*/ },
                    drawable = R.drawable.img_add_user,
                    drawable2 = Icons.Filled.Check
                )
            }
        },
        modifier = Modifier.background(color = Color(0xff181f36))
    ) {
        trip_valus()
    }
}


@Preview
@Composable
fun AddTripPreview() {
    AddTrip()
}

