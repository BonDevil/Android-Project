package com.example.anrdoidteamproject.interfaces

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
import androidx.compose.material.Divider

import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

class FriendsList : BaseScreen() {


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
    override fun mainScreen() {
        Column() {
            topBar(message = stringResource(R.string.znajomi))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.925f)
                    .background(color = Color(0xff181f36))
            ) {
                Listpersons(SampleData.conversationSample)

            }
            bottomBar()
        }
    }
}


object SampleData {
    // Sample conversation data
    val conversationSample = listOf(
        FriendsList.Osoba(
            "Jakub",
            "Roszkowski"
        ),
        FriendsList.Osoba(
            "Piotr",
            "Grygoruk"
        ),
        FriendsList.Osoba(
            "Nataliia",
            "Martynenko"
        ),
        FriendsList.Osoba(
            "Adam",
            "Nowak"
        ),
        FriendsList.Osoba(
            "Jan",
            "Kowalski"
        )
    )
}