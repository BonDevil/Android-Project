package com.example.anrdoidteamproject.interfaces

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anrdoidteamproject.R

open class BaseScreen {

    @Composable
    open fun topBar(message: String) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .background(Color(44, 57, 100))
                .height(60.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = message,
//                zmienic fonta na ten century gothic, cos nie wyszukuje
                fontFamily = FontFamily.SansSerif,
                fontSize = 40.sp,
                fontWeight = FontWeight(750),
                color = Color.White
            )

        }
    }

    @Composable
    open fun bottomBar() {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .background(Color(44, 57, 100))
                .height(60.dp)
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
    open fun mainScreen() {
        Column() {
            topBar(message = "ZNAJOMI")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
//                        zrobic zeby to sie robilo dynamicznie
                    .height(500.dp)
                    .background(color = Color(0xff181f36))){

            }
            bottomBar()
        }

    }
}