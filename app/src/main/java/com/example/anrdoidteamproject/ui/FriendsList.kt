package com.example.anrdoidteamproject.ui

import androidx.compose.foundation.background

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material.Text
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.stringResource

import androidx.compose.ui.unit.dp
import com.example.anrdoidteamproject.R

import androidx.compose.foundation.layout.Column

import androidx.compose.runtime.Composable


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer


import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.layout.width
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.anrdoidteamproject.ui.theme.bottomBar
import com.example.anrdoidteamproject.ui.theme.topBar


data class Osoba(val Imie: String, val Nazwisko: String)


@Composable
fun PersonCard(per: Osoba) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(modifier = Modifier.padding(20.dp)) {
            Text(
                text = per.Imie,
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = FontFamily(
                    Font(R.font.century_gothic)
                )
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = per.Nazwisko,
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = FontFamily(
                    Font(R.font.century_gothic)
                )
            )
            Spacer(modifier = Modifier.width(1.dp))

        }
        Divider(color = Color.White, thickness = 2.dp)

    }

}


@Composable
fun Listpersons(osobas: List<Osoba>) {
    LazyColumn {
        osobas.map { item { PersonCard(it) } }
    }
}


@Composable
fun FriendsList(
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
        topBar = { topBar(message = stringResource(R.string.znajomi)) },
        modifier = Modifier.background(color = Color(0xff181f36))

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color(0xff181f36))
        ) {
            Listpersons(SampleData.conversationSample)
        }
    }
}


@Preview(heightDp = 1000)
@Composable
fun FirendsListPreview() {
    FriendsList()
}


object SampleData {
    // Sample conversation data
    val conversationSample = listOf(
        Osoba(
            "Jakub",
            "Roszkowski"
        ),
        Osoba(
            "Piotr",
            "Grygoruk"
        ),
        Osoba(
            "Nataliia",
            "Martynenko"
        ),
        Osoba(
            "Adam",
            "Nowak"
        ),
        Osoba(
            "Jan",
            "Kowalski"
        )
    )
}












