package com.example.anrdoidteamproject.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.anrdoidteamproject.AppScreens
import com.example.anrdoidteamproject.R
import com.example.anrdoidteamproject.businessLogic.*
import com.example.anrdoidteamproject.ui.theme.*


@Composable
fun TripCard(
    trip: Trip,
    navController: NavController = rememberNavController(),

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
                    navController.navigate(AppScreens.Stats.name)


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

            }
        }
        Divider(color = Color.White, thickness = 2.dp)

    }


}


@Composable
fun ListTrips(trips: List<Trip>,navController: NavController = rememberNavController()) {
    LazyColumn {
        trips.map { item { TripCard(it,navController) } }
    }
}


@Composable
fun TripsList(
    userInfoButtonOnClick: () -> Unit = {},
    homeButtonOnClick: () -> Unit = {},
    settingsButtonOnClick: () -> Unit = {},
    addtripButtonOnClick: () -> Unit = {},
    statsButtonOnClick: () -> Unit = {},
    navController: NavController = rememberNavController()


    ) {

    val db = DatabaseConnection()
    db.loadFriends()

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
                .background(color = Color(0xff181f36)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
            ) {

                ListTrips(trips = SampleData_trip.SampleTrips,navController)
                //Preview_MultipleRadioButtonsTrip()
            }

//            Row(
//            ) {
//                PromptButton(
//                    label = R.string.Szczegóły,
//                    onClick = statsButtonOnClick
//                )
//            }

        }

    }

}


@Composable
fun Preview_MultipleRadioButtonsTrip() {
    val selectedValue = remember { mutableStateOf("") }

    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }

    val items = SampleData_trip.SampleTrips
    Column(Modifier.padding(8.dp)) {
//        Text(text = "Selected value: ${selectedValue.value.ifEmpty { "NONE" }}")
        items.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .selectable(
                        selected = isSelectedItem(item.tripName),
                        onClick = {
                            onChangeState(item.tripName)
                            selectedPerson = item.tripName
                            transferData(item)
                        },
                        role = Role.RadioButton
                    )
            ) {


                Column(
                    modifier = Modifier
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = isSelectedItem(item.tripName),
                            onClick = null,
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color.White,
                                unselectedColor = Color.White,
                                disabledColor = Color.LightGray
                            )
                        )
                        Spacer(modifier = Modifier.width(15.dp))
//                        OutlinedButton(
//                            onClick = {
//
//                                transferData(item)
//
//
//                            },
//                            Modifier.width(200.dp),
//                            colors = ButtonDefaults.buttonColors(
//                                backgroundColor = Color(24, 31, 54),
//                                disabledBackgroundColor = Color(70, 99, 255),
//                            )
//                        ) {
                        Text(
                            text = item.tripName,
                            color = Color.White,
                            fontSize = 40.sp,
                            fontFamily = FontFamily(
                                Font(R.font.century_gothic)
                            )
                        )

//                        }


                    }
                    Divider(color = Color.White, thickness = 2.dp)

                }


//                RadioButton(
//                    selected = isSelectedItem(item.email),
//                    onClick = null
//                )
//                Text(
//                    text = item.firstName,
//                    modifier = Modifier.fillMaxWidth()
//                )
            }
        }
    }
}


object SampleData_trip {

    val SampleTrips = listOf(

        Trip(
            constructortestowytest = "",
            "Barcelona",
            "opis",
            1000.0,
            7,
            130.0,
            120.0,
            200.0,
            300.0,
            200.0,
            80.0,
            historySample,
            historyReturnsSample
        ),
        Trip(
            constructortestowytest = "",
            "Madryt",
            "opis",
            2000.0,
            10,
            180.0,
            450.0,
            200.0,
            300.0,
            270.0,
            180.0,
            historySample2,
            historyReturnsSample
        ),
        Trip(
            constructortestowytest = "",
            "Lizbona",
            "opis",
            200.0,
            3,
            130.0,
            120.0,
            300.0,
            300.0,
            200.0,
            80.0,
            historySample3,
            historyReturnsSample
        ),
        Trip(
            constructortestowytest = "",
            "Warszawa",
            "opis",
            5000.0,
            5,
            530.0,
            920.0,
            600.0,
            670.0,
            600.0,
            580.0,
            historySample4,
            historyReturnsSample
        ),

        )
}

val historySample = listOf(
    Expenditure("", "Jan Nowak", "cat1food", 20.86, "Jedzenie", "10-08-2020"),
    Expenditure("", "Leo Messi", "cat1food", 120.86, "Piłeczka", "10-08-2020"),
    Expenditure("", "Wilfredo León", "cat1food", 800.86, "Siateczka", "15-02-2020"),
    Expenditure("", "Jan Kowalski", "cat1food", 20.86, "Transport", "19-01-2020"),
    Expenditure("", "Adam Nowak", "cat1food", 800.86, "Hotel", "18-08-2020"),
    Expenditure("", "Jakub Roszkowski", "cat1food", 800.86, "Jedzenie", "10-03-2020"),
    Expenditure("", "Piotr Grygoruk", "cat1food", 24.86, "Picie", "20-08-2020"),
    Expenditure("", "Piotr Grygoruk", "cat1food", 24.86, "Wiecej Picia", "20-08-2020"),
)


val historySample2 = listOf(
    Expenditure("", "Jan Nowak", "cat1food", 20.86, "Jedzenie", "10-08-2020"),
    Expenditure("", "Leo Messi", "cat1food", 120.86, "Piłeczka", "10-08-2020"),
    Expenditure("", "Jakub Roszkowski", "cat1food", 800.86, "Jedzenie", "10-03-2020"),
    Expenditure("", "Piotr Grygoruk", "cat1food", 24.86, "Picie", "20-08-2020"),
    Expenditure("", "Piotr Grygoruk", "cat1food", 24.86, "Wiecej Picia", "20-08-2020"),
)


val historySample3 = listOf(
    Expenditure("", "Jan Nowak", "cat1food", 20.86, "Jedzenie", "10-08-2020"),
    Expenditure("", "Leo Messi", "cat1food", 120.86, "Piłeczka", "10-08-2020"),
)


val historySample4 = listOf(
    Expenditure("", "Jakub Roszkowski", "cat1food", 800.86, "Jedzenie", "10-03-2020"),
    Expenditure("", "Piotr Grygoruk", "cat1food", 24.86, "Picie", "20-08-2020"),
    Expenditure("", "Piotr Grygoruk", "cat1food", 24.86, "Wiecej Picia", "20-08-2020"),
)


val historyReturnsSample = listOf(

    TransferMoney("","Jan Nowak","Adam Kowalski",21.37,"12-4-2022"),
    TransferMoney("","Jan Roszkowski","Adam Kowalski",37.37,"12-3-2022"),
    TransferMoney("","Jan Nowak","Jakub Roszkowski",435.37,"12-7-2022"),
    TransferMoney("","Piotr Grygoruk","Adam Kowalski",43.37,"19-4-2022"),
    TransferMoney("","Jan Kowalski","Adam Kowalski",21.37,"12-4-2022"),
    TransferMoney("","Jan Roszkowski","Leo Messi",37.37,"12-3-2020"),
    TransferMoney("","Jan Nowak","Jakub Roszkowski",5.37,"26-7-2022"),
    TransferMoney("","Piotr Grygoruk","Adam Kowalski",0.37,"30-4-2020"),
)




@Preview
@Composable
fun TripsListPreview() {
    TripsList()
}
