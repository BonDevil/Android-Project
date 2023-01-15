package com.example.anrdoidteamproject.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
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
import com.example.anrdoidteamproject.R
import com.example.anrdoidteamproject.businessLogic.Expenditure
import com.example.anrdoidteamproject.businessLogic.TransferMoney
import com.example.anrdoidteamproject.ui.theme.*
import com.example.anrdoidteamproject.businessLogic.expenses
import com.example.anrdoidteamproject.businessLogic.historyReturns
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Composable
fun HistoryReturns(
    userInfoButtonOnClick: () -> Unit = {},
    homeButtonOnClick: () -> Unit = {},
    settingsButtonOnClick: () -> Unit = {},
    topbarButton: () -> Unit = {},
    HistoryButton: () -> Unit = {},
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
                onClick = topbarButton
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = HistoryButton,
                shape = RectangleShape,
                backgroundColor = Color(26, 51, 101),
            ) {

                Text(
                    text = stringResource(R.string.expenses), color = Color.White,
                    fontSize = 20.sp
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        modifier = Modifier.background(color = Color(0xff181f36))

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color(0xff181f36))
        ) {

            ListRET(returns = historyReturns)

        }
    }


}


@Composable
fun ReturnCard(ret: TransferMoney) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),

        ) {
        Row(modifier = Modifier.align(Alignment.Start)) {
            Text(
                text = stringResource(R.string.zaplacone_przez),
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily(
                    Font(R.font.century_gothic)
                )
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = ret.paying_person,
                color = Color.Red,
                fontSize = 20.sp,
                fontFamily = FontFamily(
                    Font(R.font.century_gothic)
                )
            )
        }
        Row(modifier = Modifier.align(Alignment.Start)) {
            Text(
                text = stringResource(R.string.zaplacone_przez),
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily(
                    Font(R.font.century_gothic)
                )
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = ret.receiving_person,
                color = Color.Green,
                fontSize = 20.sp,
                fontFamily = FontFamily(
                    Font(R.font.century_gothic)
                )
            )
        }
        Row(modifier = Modifier.align(Alignment.End)) {
            Text(
                text = ret.value.toString(),
                color = Color.Cyan,
                fontSize = 40.sp,
                fontFamily = FontFamily(
                    Font(R.font.century_gothic)
                )
            )
        }


        Row(modifier = Modifier.align(Alignment.End)) {
            Text(
                text = ret.date,
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
fun ListRET(returns: List<TransferMoney>) {
    LazyColumn(modifier = Modifier.padding(bottom = 180.dp)) {
        returns.map { item { ReturnCard(it) } }
    }
}