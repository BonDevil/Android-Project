package com.example.anrdoidteamproject.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anrdoidteamproject.R
import com.example.anrdoidteamproject.ui.theme.ConfirmButton
import com.example.anrdoidteamproject.ui.theme.SimpleTextField
import com.example.anrdoidteamproject.ui.theme.bottomBar
import com.example.anrdoidteamproject.ui.theme.topBar

//@Composable
//fun categoryValues() {
//    Column(
//        modifier = Modifier
//            .background(Color(24, 31, 54))
//            .padding(20.dp)
//            .fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ) {
//
//
//        Column(
//            modifier = Modifier
//                .padding(20.dp)
//
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.Start,
//        )
//        {
//
//            Text(
//                text = stringResource(R.string.nazwa),
//                color = Color.White,
//                fontSize = 20.sp,
//                fontFamily = FontFamily(
//                    Font(R.font.century_gothic)
//                ),
//            )
//            Spacer(modifier = Modifier.height(15.dp))
////        SimpleTextField(
////
////        )
//            TextField(
//                value = "",
//                onValueChange = {},
//                modifier = Modifier
//                    .border(2.dp, Color(89, 128, 255), RoundedCornerShape(10))
//                    .background(Color(217, 217, 217), RoundedCornerShape(10))
//                    .heightIn(min = 56.dp),
//
//                )
//            Spacer(modifier = Modifier.height(20.dp))
//            Text(
//                text = stringResource(R.string.opis),
//                color = Color.White,
//                fontSize = 20.sp,
//                fontFamily = FontFamily(
//                    Font(R.font.century_gothic)
//                ),
//            )
//            Spacer(modifier = Modifier.height(15.dp))
//            TextField(
//                value = "",
//                onValueChange = {},
//                modifier = Modifier
//                    .border(2.dp, Color(89, 128, 255), RoundedCornerShape(10))
//                    .background(Color(217, 217, 217), RoundedCornerShape(10))
//                    .heightIn(min = 56.dp)
//            )
//            Spacer(modifier = Modifier.height(20.dp))
//            Text(
//                text = stringResource(R.string.dodaj_ikone),
//                color = Color.White,
//                fontSize = 20.sp,
//                fontFamily = FontFamily(
//                    Font(R.font.century_gothic)
//                ),
//            )
//            Spacer(modifier = Modifier.height(15.dp))
//            /// TODO:  Dodac dodawanie ikony
//            Spacer(modifier = Modifier.height(60.dp))
//            Text(
//                text = stringResource(R.string.wybierz_kolor),
//                color = Color.White,
//                fontSize = 20.sp,
//                fontFamily = FontFamily(
//                    Font(R.font.century_gothic)
//                ),
//            )
//            Spacer(modifier = Modifier.height(60.dp))
//            // TODO: Dodac wybieranie koloru
//            Spacer(modifier = Modifier.height(60.dp))
//
//        }
//
//
//    }
//}
//
//
//@Composable
//fun AddCategory(
//    userInfoButtonOnClick: () -> Unit = {},
//    homeButtonOnClick: () -> Unit = {},
//    settingsButtonOnClick: () -> Unit = {}
//) {
//    Scaffold(
//        bottomBar = {
//            bottomBar(
//                userInfoButtonOnClick = userInfoButtonOnClick,
//                homeButtonOnClick = homeButtonOnClick,
//                settingsButtonOnClick = settingsButtonOnClick
//            )
//        },
//        topBar = { topBar(message = stringResource(R.string.dodaj_kategorie)) },
//        floatingActionButton = {
//            ConfirmButton(confirmOnClick = { /*TODO*/ }
//            )
//        }
//    ) {
//        categoryValues()
//    }
//
//
//}
//
//@Preview
//@Composable
//fun AddCategoryPreview() {
//    AddCategory()
//}


//mozliwe ze w nowej wersji





