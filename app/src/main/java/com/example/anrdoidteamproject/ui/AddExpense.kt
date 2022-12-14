package com.example.anrdoidteamproject.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anrdoidteamproject.R
import com.example.anrdoidteamproject.businessLogic.Trip
import com.example.anrdoidteamproject.ui.theme.ConfirmButton
import com.example.anrdoidteamproject.ui.theme.TextFieldWithLabel
import com.example.anrdoidteamproject.ui.theme.bottomBar
import com.example.anrdoidteamproject.ui.theme.topBar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
var category = "food"




@Composable
fun DropdownCategories() {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf(
        stringResource(R.string.cat_jedzenie),
        stringResource(R.string.cat_spanie),
        stringResource(R.string.cat_napoje),
        stringResource(R.string.cat_atrakcje),
        stringResource(R.string.cat_samolot),
        stringResource(R.string.cat_transport),
    )

    var selectedIndex by remember { mutableStateOf(0) }
    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart)
            .border(2.dp, Color(89, 128, 200), RoundedCornerShape(10))
    ) {
        Text(

            items[selectedIndex],
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .clickable(onClick = { expanded = true })
,
            fontSize = 30.sp,
            color = Color.White,

            )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()


        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                }) {
                    Text(
                        text = s,
                        fontSize = 30.sp,
                    )
                }
            }
        }
    }
    category = items[selectedIndex].toString()
}

@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddExpense(
    userInfoButtonOnClick: () -> Unit = {},
    homeButtonOnClick: () -> Unit = {},
    settingsButtonOnClick: () -> Unit = {},

) {
    var expenseName = mutableStateOf("")
    var expenseSUM = mutableStateOf("")
    Scaffold(
        bottomBar = {
            bottomBar(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        },
        topBar = { topBar(message = stringResource(R.string.dodaj_wydatek)) },
        floatingActionButton = {
            ConfirmButton(confirmOnClick = {
                val myExpense = com.example.anrdoidteamproject.businessLogic.Expenditure(
                    paying_person = Firebase.auth.currentUser.hashCode(), category = category, value = expenseSUM.value.toDouble(), name = expenseName.value.toString(),listOf()
                )
            /*TODO*/

            }
            )
        },
        modifier = Modifier.background(color = Color(0xff181f36))

    ) {
        Column(
            modifier = Modifier
                .background(Color(24, 31, 54))
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxHeight()
                    .fillMaxWidth(0.85f),
                horizontalAlignment = Alignment.Start,

                )
            {
                Spacer(modifier = Modifier.height(10.dp))
                TextFieldWithLabel(
                    KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    label = R.string.nazwa,
                    expenseName
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(R.string.kategoria),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(
                        Font(R.font.century_gothic)
                    ),
                )
                Spacer(modifier = Modifier.height(10.dp))
                DropdownCategories()
                Spacer(modifier = Modifier.height(20.dp))

                TextFieldWithLabel(
                    KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    label = R.string.kwota,
                    expenseSUM
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = stringResource(R.string.znajomi),
                    color = Color.White,
                    fontSize = 20.sp,
                )
                Spacer(modifier = Modifier.height(15.dp))


                Divider(color = Color.White, thickness = 2.dp)
                Listpersons3(SampleData3.conversationSample)


            }
        }
    }
}
