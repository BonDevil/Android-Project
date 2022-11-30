package com.example.anrdoidteamproject.interfaces

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anrdoidteamproject.R

class TripsList : BaseScreen() {


    data class Wycieczka(val Name: String)


    @Composable
    fun TripCard(trip: Wycieczka) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = trip.Name,
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = FontFamily(
                        Font(R.font.century_gothic)
                    )
                )

                Spacer(modifier = Modifier.width(4.dp))

                Spacer(modifier = Modifier.width(1.dp))

            }
            Divider(color = Color.White, thickness = 2.dp)

        }

    }


    @Composable
    fun ListTrips(osobas: List<Wycieczka>) {
        LazyColumn {
            osobas.map { item { TripCard(it) } }
        }
    }


    @Composable
    override fun mainScreen() {
        Column() {
            topBar(message = stringResource(R.string.wycieczki))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.925f)
                    .background(color = Color(0xff181f36))){
                ListTrips(SampleData_trip.conversationSample)
            }
            bottomBar()
        }
    }
}



object SampleData_trip {

    val conversationSample = listOf(
        TripsList.Wycieczka(
            "Barcelona"
        ),
        TripsList.Wycieczka(
            "Madryt"
        ),
        TripsList.Wycieczka(
            "Lizbona"
        ),
        TripsList.Wycieczka(
            "Warszawa"
        ),
        TripsList.Wycieczka(
            "Paryż"
        )
    )
}