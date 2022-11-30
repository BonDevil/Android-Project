package com.example.anrdoidteamproject.interfaces

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anrdoidteamproject.R

class Settings : BaseScreen() {

    @Composable
    open fun choose_language() {

        Column(
            modifier = Modifier
                .padding(20.dp)
                .background(Color(24, 31, 54))
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,

            )
        {

            Text(
                text = stringResource(R.string.jezyk),
                color = Color.White,
                fontSize = 30.sp,
            )
            Spacer(modifier = Modifier.height(15.dp))
            DropdownDemo()

        }
    }

    @Composable
    fun DropdownDemo() {
        var expanded by remember { mutableStateOf(false) }
        val items = listOf(
            stringResource(R.string.polski),
            stringResource(R.string.angielski),
            stringResource(R.string.wloski),
            stringResource(R.string.hiszpanski),
            stringResource(R.string.niemiecki)
        )

        var selectedIndex by remember { mutableStateOf(0) }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.TopStart)
        ) {
            Text(

                items[selectedIndex], modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { expanded = true })
                    .background(
                        Color.White
                    ),fontSize = 30.sp,

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
                        Text(text = s,
                                fontSize = 30.sp,)
                    }
                }
            }
        }
    }

    @Composable
    override fun mainScreen() {
        Column() {
            topBar(message = stringResource(R.string.ustawienia))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.925f)
                    .background(color = Color(0xff181f36))
            ) {
                choose_language()

            }
            bottomBar()
        }
    }
}