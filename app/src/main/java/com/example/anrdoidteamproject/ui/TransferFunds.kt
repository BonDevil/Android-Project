package com.example.anrdoidteamproject.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anrdoidteamproject.R
import com.example.anrdoidteamproject.ui.theme.SimpleTextField
import com.example.anrdoidteamproject.ui.theme.TextFieldWithLabel
import com.example.anrdoidteamproject.ui.theme.bottomBar
import com.example.anrdoidteamproject.ui.theme.topBar


@Composable
fun transferFunds() {

    Column(
        modifier = Modifier
            .padding(30.dp)
            .background(Color(24, 31, 54))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

        )
    {

        TextFieldWithLabel(
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            label = R.string.kwota


        )

        Text(
            text = stringResource(R.string.znajomi),
            color = Color.White,
            fontSize = 30.sp,
        )
        Spacer(modifier = Modifier.height(15.dp))


        Divider(color = Color.White, thickness = 2.dp)
        Listpersons3(SampleData3.conversationSample)


    }
}

@Composable
fun PersonCard3(per: Osoba) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row() {
            CheckBoxDemo()
            Text(
                text = per.Imie,
                color = Color.White,
                fontSize = 25.sp,
                fontFamily = FontFamily(
                    Font(R.font.century_gothic)
                )
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = per.Nazwisko,
                color = Color.White,
                fontSize = 25.sp,
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
fun Listpersons3(osobas: List<Osoba>) {
    LazyColumn( contentPadding = PaddingValues(vertical = 40.dp)

    ) {
        osobas.map { item { PersonCard3(it) } }

    }
}


object SampleData3 {
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


@Composable
fun TransferFunds() {
    Scaffold(
        bottomBar = { bottomBar() },
        topBar = { topBar(message = stringResource(R.string.zwroc_koszty)) },
        modifier = Modifier.background(color = Color(0xff181f36))

    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.925f)
                .background(color = Color(0xff181f36))
        ) {
            transferFunds()
        }
    }
}

@Preview
@Composable
fun TransferFundsPreview() {
    TransferFunds()
}

