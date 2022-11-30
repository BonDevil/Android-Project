package com.example.anrdoidteamproject.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.anrdoidteamproject.R
import com.example.anrdoidteamproject.ui.theme.bottomBar
import com.example.anrdoidteamproject.ui.theme.topBar


@Composable
fun TransferFunds() {
    Column() {
        topBar(message = stringResource(R.string.zwroc_koszty))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.925f)
                .background(color = Color(0xff181f36))
        ) {

        }
        bottomBar()
    }
}

@Preview
@Composable
fun TransferFundsPreview(){
    TransferFunds()
}

