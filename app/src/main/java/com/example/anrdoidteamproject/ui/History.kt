package com.example.anrdoidteamproject.ui

import android.provider.MediaStore.DownloadColumns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anrdoidteamproject.R
import com.example.anrdoidteamproject.ui.theme.*


@Composable
fun History(
    userInfoButtonOnClick: () -> Unit = {},
    homeButtonOnClick: () -> Unit = {},
    settingsButtonOnClick: () -> Unit = {},
    topbarButton: () -> Unit = {},
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
        modifier = Modifier.background(color = Color(0xff181f36))

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color(0xff181f36))
        ) {

                ListEx(expenses = SampleDataExpenditure.conversationSample)

        }
    }


}

@Preview
@Composable
fun HistoryPreview() {
    History()
}











data class Expenditure(
    val category: String,
    val paying_person_name: String,
    val paying_person_full_name: String,
    val date: String,
    val price: Double
)


@Composable
fun ExpenditureCard(ex: Expenditure) {
    Column(
        modifier = Modifier
            .fillMaxSize().padding(10.dp),

    ) {
        Row(modifier = Modifier.align(Alignment.Start)) {
            Text(
                text = ex.category,
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = FontFamily(
                    Font(R.font.century_gothic)
                )
            )
        }
        Row(modifier = Modifier.align(Alignment.End)) {
            Text(
                text = ex.price.toString(),
                color = Color.Cyan,
                fontSize = 40.sp,
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
                text = ex.paying_person_name,
                color = Color.Red,
                fontSize = 20.sp,
                fontFamily = FontFamily(
                    Font(R.font.century_gothic)
                )
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = ex.paying_person_full_name,
                color = Color.Red,
                fontSize = 20.sp,
                fontFamily = FontFamily(
                    Font(R.font.century_gothic)
                )
            )
            Spacer(modifier = Modifier.width(1.dp))

        }
        Row(modifier = Modifier.align(Alignment.End)) {
            Text(
                text = ex.date,
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


object SampleDataExpenditure {
    // Sample conversation data
    val conversationSample = listOf(
        Expenditure(
            category = "Jedzenie",
            paying_person_name = "Jan",
            paying_person_full_name = "Nowak",
            date = "23.10.2020",
            price = 20.86
        ),
        Expenditure(
            category = "Transport",
            paying_person_name = "Jan",
            paying_person_full_name = "Kowalski",
            date = "23.10.2020",
            price = 20.86
        ),
        Expenditure(
            category = "Hotel",
            paying_person_name = "Adam",
            paying_person_full_name = "Nowak",
            date = "23.10.2022",
            price = 20.86
        ),
        Expenditure(
            category = "Jedzenie",
            paying_person_name = "Jakub",
            paying_person_full_name = "Roszkowski",
            date = "23.11.2020",
            price = 120.86
        ),
        Expenditure(
            category = "Picie",
            paying_person_name = "Piotr",
            paying_person_full_name = "Grygoruk",
            date = "23.12.2020",
            price = 24.86
        )
    )
}