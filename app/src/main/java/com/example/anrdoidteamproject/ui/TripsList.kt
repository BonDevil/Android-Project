package com.example.anrdoidteamproject.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.anrdoidteamproject.R
import com.example.anrdoidteamproject.businessLogic.Trip
import com.example.anrdoidteamproject.ui.theme.*
import com.example.anrdoidteamproject.businessLogic.transferData
import com.example.anrdoidteamproject.businessLogic.tripName
import com.example.anrdoidteamproject.businessLogic.tripDescription
import com.example.anrdoidteamproject.businessLogic.plannedAmount
import com.example.anrdoidteamproject.businessLogic.numberOfDays
import com.example.anrdoidteamproject.businessLogic.cat1foodMax
import com.example.anrdoidteamproject.businessLogic.cat2sleepMax
import com.example.anrdoidteamproject.businessLogic.cat3drinkMax
import com.example.anrdoidteamproject.businessLogic.cat4atractionsMax
import com.example.anrdoidteamproject.businessLogic.cat5planeMax
import com.example.anrdoidteamproject.businessLogic.cat6transportMax
import com.example.anrdoidteamproject.businessLogic.cat1food
import com.example.anrdoidteamproject.businessLogic.cat2sleep
import com.example.anrdoidteamproject.businessLogic.cat3drink
import com.example.anrdoidteamproject.businessLogic.cat4atractions
import com.example.anrdoidteamproject.businessLogic.cat5plane
import com.example.anrdoidteamproject.businessLogic.cat6transport
import com.example.anrdoidteamproject.businessLogic.TotalAmount


@Composable
fun TripCard
            (
    trip: Trip,
//    navController: NavController = rememberNavController(),
    statsButtonOnClick: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(modifier = Modifier.padding(20.dp)) {


            OutlinedButton(
                onClick = {

                    transferData(trip)


 //                  statsButtonOnClick
//                    navController.navigate(AppScreens.Stats.name)
                },
                Modifier.width(200.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(24, 31, 54),
                    disabledBackgroundColor = Color(70, 99, 255),
                )
            ) {
                Text(
                    text = trip.tripName,
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = FontFamily(
                        Font(R.font.century_gothic)
                    )
                )

            Spacer(modifier = Modifier.width(4.dp))

            Spacer(modifier = Modifier.width(1.dp))

        }}
        Divider(color = Color.White, thickness = 2.dp)

    }


}


@Composable
fun ListTrips(osobas: List<Trip>) {
    LazyColumn {
        osobas.map { item { TripCard(it) } }
    }
}


@Composable
fun TripsList(
    userInfoButtonOnClick: () -> Unit = {},
    homeButtonOnClick: () -> Unit = {},
    settingsButtonOnClick: () -> Unit = {},
    addtripButtonOnClick: () -> Unit = {},
    statsButtonOnClick: () -> Unit = {},

) {

        Scaffold(
            bottomBar = {
                bottomBar(
                    userInfoButtonOnClick = userInfoButtonOnClick,
                    homeButtonOnClick = homeButtonOnClick,
                    settingsButtonOnClick = settingsButtonOnClick
                )
            },
            topBar = { topBar(message = stringResource(R.string.wycieczki)) },
            modifier = Modifier.background(color = Color(0xff181f36)),
            floatingActionButton = {
                AddButton(
                    confirmOnClick = addtripButtonOnClick
                )
            },

            ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.925f)
                    .background(color = Color(0xff181f36))
            ) {
                Row(
                ) {

                    ListTrips(SampleData_trip.SampleTrips)
                }
                Row(
                ) {
                    PromptButton(
                        label = R.string.test,
                        onClick = statsButtonOnClick
                    )
                }

            }

        }

}

object SampleData_trip {

    val SampleTrips = listOf(

        Trip(constructortestowytest = "","Barcelona","opis",1000.0,7,130.0,120.0,200.0,300.0,200.0,80.0,
            listOf()),
        Trip(constructortestowytest = "","Madryt","opis",2000.0,10,180.0,450.0,200.0,300.0,270.0,180.0,
            listOf()),
        Trip(constructortestowytest = "","Lizbona","opis",200.0,3,130.0,120.0,300.0,300.0,200.0,80.0,
            listOf()),
        Trip(constructortestowytest = "","Warszawa","opis",5000.0,5,530.0,920.0,600.0,670.0,600.0,580.0,
            listOf()),

    )
}


@Preview
@Composable
fun TripsListPreview() {
    TripsList()
}
