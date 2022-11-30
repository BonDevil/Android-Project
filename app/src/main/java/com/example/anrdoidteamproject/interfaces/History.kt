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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anrdoidteamproject.R

class History : BaseScreen() {

    @Composable
    open fun topBar2(message: String, message2: String) {

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
                color = Color.White
            )
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
    override fun mainScreen() {
        Column() {
            topBar2(message = stringResource(R.string.historia),message2 = stringResource(R.string.bilans))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.925f)
                    .background(color = Color(0xff181f36))){

            }
            bottomBar()
        }
    }
}