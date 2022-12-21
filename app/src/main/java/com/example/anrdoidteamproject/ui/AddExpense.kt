package com.example.anrdoidteamproject.ui

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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anrdoidteamproject.R
import com.example.anrdoidteamproject.ui.theme.ConfirmButton
import com.example.anrdoidteamproject.ui.theme.TextFieldWithLabel
import com.example.anrdoidteamproject.ui.theme.bottomBar
import com.example.anrdoidteamproject.ui.theme.topBar

@Composable
fun add_expense() {
    Column(
        modifier = Modifier
            .padding(20.dp)
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
            Text(
                text = stringResource(R.string.nazwa),
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily(
                    Font(R.font.century_gothic)
                ),
            )
            Spacer(modifier = Modifier.height(10.dp))
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
                    .heightIn(min = 20.dp),

                )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(R.string.kategoria),
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily(
                    Font(R.font.century_gothic)
                ),
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .border(2.dp, Color(89, 128, 255), RoundedCornerShape(10))
                    .background(Color(217, 217, 217), RoundedCornerShape(10))
                    .heightIn(min = 20.dp),
//
//            KeyboardOptions(
//                keyboardType = KeyboardType.Text,
//                imeAction = ImeAction.Next
//            ),
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(R.string.kwota),
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily(
                    Font(R.font.century_gothic)
                ),
            )
            Spacer(modifier = Modifier.height(10.dp))
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
                    .heightIn(min = 20.dp),

                )
            Spacer(modifier = Modifier.height(20.dp))
            /*TextFieldWithLabel(

            KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            label = R.string.nazwa,

            )

        Spacer(modifier = Modifier.height(15.dp))
        DropdownCategories()

        Spacer(modifier = Modifier.height(15.dp))


        TextFieldWithLabel(
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            label = R.string.kwota,
//            modifierLocalOf {Modifier} = Modifier
//                .background(Color(217,217,217), RoundedCornerShape(10))
//                .heightIn(min = 56.dp),
        )
        Spacer(modifier = Modifier.height(15.dp))*/


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
    ) {
        Text(

            items[selectedIndex],
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .clickable(onClick = { expanded = true })
                .background(
                    color = Color(200, 200, 200)
                ),
            fontSize = 30.sp,

            )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(200, 200, 200)
                )

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
}


@Composable
fun AddExpense(
    userInfoButtonOnClick: () -> Unit = {},
    homeButtonOnClick: () -> Unit = {},
    settingsButtonOnClick: () -> Unit = {}
) {
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
            ConfirmButton(confirmOnClick = { /*TODO*/ }
            )
        },
        modifier = Modifier.background(color = Color(0xff181f36))

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color(0xff181f36))
        ) {
            add_expense()
        }

    }
}


@Preview
@Composable
fun AddExpensePreview() {
    AddExpense()
}
