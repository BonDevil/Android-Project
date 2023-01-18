package com.example.anrdoidteamproject.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.anrdoidteamproject.AppScreens
import com.example.anrdoidteamproject.R
import com.example.anrdoidteamproject.businessLogic.Expenditure
import com.example.anrdoidteamproject.ui.theme.*
import com.example.anrdoidteamproject.businessLogic.expenses


@Composable
fun History(
    userInfoButtonOnClick: () -> Unit = {},
    homeButtonOnClick: () -> Unit = {},
    settingsButtonOnClick: () -> Unit = {},
    topbarButton: () -> Unit = {},
    historyReturnsButton: () -> Unit = {},
    navController: NavController = rememberNavController()
) {

    Scaffold(
        bottomBar = {
            bottomBar(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        },
        topBar = {
            topBar22(
                message = stringResource(R.string.historia),
                message2 = stringResource(R.string.bilans),
                onClick = {
                    navController.popBackStack()
                    navController.navigate(AppScreens.Balance.name)}
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = historyReturnsButton,
                shape = RectangleShape,
                backgroundColor = Color(26, 51, 101),
            ) {

                Text(text = stringResource(R.string.refunds), color = Color.White,
                fontSize = 20.sp)
            }
        }
        ,
        floatingActionButtonPosition = FabPosition.Center,
        modifier = Modifier.background(color = Color(0xff181f36))

    ) {
        Row(

            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color(0xff181f36))
        ) {

                ListEx(expenses = expenses.asReversed())

        }
    }


}

@Preview
@Composable
fun HistoryPreview() {
    History()
}














@Composable
fun ExpenditureCard(expenditure: Expenditure) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),

    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Column() {
                Text(
                    text = expenditure.name,
                    color = Color(0xFF5980FF),
                    fontSize = 25.sp,
                    fontFamily = FontFamily(
                        Font(R.font.century_gothic)
                    )
                )
            }


        Column(modifier = Modifier) {
            Text(
                text = expenditure.value.toString(),
                color = Color(0xFF5980FF),
                fontSize = 25.sp,
                fontFamily = FontFamily(
                    Font(R.font.century_gothic)
                )
            )
        }
    }
        Row(modifier = Modifier.fillMaxWidth(),

            verticalAlignment = Alignment.CenterVertically) {

            Column() {
                Text(
                    text = stringResource(R.string.zaplacone_przez)+" ",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(
                        Font(R.font.century_gothic)
                    )
                )
            }
            Column() {
                Text(
                    text = expenditure.paying_person,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(
                        Font(R.font.century_gothic)
                    )
                )
            }

//            Spacer(modifier = Modifier.width(6.dp))
//
//            Text(
//                text = ex.paying_person_full_name,
//                color = Color.Red,
//                fontSize = 20.sp,
//                fontFamily = FontFamily(
//                    Font(R.font.century_gothic)
//                )
//            )
            Spacer(modifier = Modifier.width(1.dp))

        }
        Row(modifier = Modifier.align(Alignment.End)) {
            Text(
                text = expenditure.date,
                color = Color.White,
                fontSize = 25.sp,
                fontFamily = FontFamily(
                    Font(R.font.century_gothic)
                )
            )
        }
        Divider(color = Color.White, thickness = 2.dp)

    }

}


@Composable
fun ListEx(expenses: List<Expenditure>) {
    LazyColumn(modifier = Modifier.padding(bottom = 180.dp)) {
        expenses.map { item { ExpenditureCard(it) } }
    }
}