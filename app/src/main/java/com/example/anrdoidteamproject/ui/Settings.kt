package com.example.anrdoidteamproject.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anrdoidteamproject.R
import com.example.anrdoidteamproject.ui.theme.bottomBar
import com.example.anrdoidteamproject.ui.theme.topBar
import java.util.*

var selectedIndex=0


@Composable
fun ChooseLanguage() {

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

    var selectedIndexTemp by rememberSaveable { mutableStateOf(selectedIndex) }


    if (selectedIndexTemp == 0) {
        translator(lan = "pl")
    }
    if (selectedIndexTemp == 1) {
        translator(lan = "en")
    }
    if (selectedIndexTemp == 2) {
        translator(lan = "it")
    }
    if (selectedIndexTemp == 3) {
        translator(lan = "es")
    }
    if (selectedIndexTemp == 4) {
        translator(lan = "de")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopStart)
    ) {
        Text(


            items[selectedIndexTemp],
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded = true })
                .background(
                    Color.White
                ),
            fontSize = 30.sp,

            ) 

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()

        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndexTemp = index
                    selectedIndex=selectedIndexTemp
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
fun Settings(
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
        topBar = { topBar(message = stringResource(R.string.ustawienia)) },
        modifier = Modifier.background(color = Color(0xff181f36))

    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color(0xff181f36))
        ) {
            ChooseLanguage()
        }
    }
}


@Preview
@Composable
fun SettingsPreview() {
    Settings()
}


@Composable
fun translator(lan: String) {
    val ct = LocalContext.current
    val locale = Locale(lan)
    Locale.setDefault(locale)
    val resources = ct.resources
    val configuration = resources.configuration
    configuration.locale = locale
    resources.updateConfiguration(configuration, resources.displayMetrics)

}
