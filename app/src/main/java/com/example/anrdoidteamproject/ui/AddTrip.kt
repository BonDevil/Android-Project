package com.example.anrdoidteamproject.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.anrdoidteamproject.R
import com.example.anrdoidteamproject.businessLogic.*
import com.example.anrdoidteamproject.ui.theme.*


var catfood = 16.0
var catsleep = 16.0
var catdrink = 16.0
var catatractions = 16.0
var catplane = 16.0
var cattransport = 16.0


var tripNameG = ""
var tripDescriptionG = ""
var plannedAmountG = ""
var numberOfDaysG = ""

@Composable
fun AddTrip(
    userInfoButtonOnClick: () -> Unit = {},
    homeButtonOnClick: () -> Unit = {},
    settingsButtonOnClick: () -> Unit = {},
    addFriendsToTrip: () -> Unit = {},
    navController: NavController = rememberNavController()
) {

    var tripName by remember { mutableStateOf(tripNameG) }
    var tripDescription by remember { mutableStateOf(tripDescriptionG) }
    var plannedAmount by remember { mutableStateOf(plannedAmountG) }
    var numberOfDays by remember { mutableStateOf(numberOfDaysG) }
    var expanded by remember { mutableStateOf(false) }
    var expandedcat by remember { mutableStateOf(false) }
    var showADDError by remember { mutableStateOf(false) }
    var showADD by remember { mutableStateOf(false) }
    Scaffold(
        bottomBar = {
            bottomBar(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        },
        topBar = { topBar(message = stringResource(R.string.dodaj_wycieczke)) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    expanded = !expanded

                    tripNameG = tripName
                    tripDescriptionG = tripDescription
                    plannedAmountG = plannedAmount.toString()
                    numberOfDaysG = numberOfDays.toString()
                },
                backgroundColor = Color.White
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_action_more_buttons),
                    tint = Color.Black,
                    contentDescription = "more",
                )
            }

            if (expanded) {
                Actionbuton2(
                    onClick = { expanded = !expanded },
                    onClick1 = addFriendsToTrip,
                    onClick2 = {/*TODO*/
                        //lista osób
                        //persons2(tu są osoby)
                        //personsUser_In_Trip_inCreate to z klasą zeby bilans był
//                        Log.d("eee", persons2.toString())
//                        Log.d("eee", personsUser_In_Trip_inCreate.get(0).id.toString())

                        if (!tripName.isNullOrEmpty() && !tripDescription.isNullOrEmpty() && !plannedAmount.isNullOrEmpty() &&
                            !numberOfDays.isNullOrEmpty() && plannedAmount.toDouble() != 0.0 && numberOfDays.toInt() != 0
                            && !persons2.isNullOrEmpty()
                        ) {
                            val tripRef = DatabaseConnection.db.getReference("trips")
                            val newTripRef = tripRef.push()
                            newTripRef.setValue(
                                Trip(
                                    tripName,
                                    tripDescription,
                                    plannedAmount = plannedAmount.toDouble(),
                                    numberOfDays.toInt(),
                                    cat1foodMax = catfood * plannedAmount.toDouble() * 0.01,
                                    cat2sleepMax = catsleep * plannedAmount.toDouble() * 0.01,
                                    cat3drinkMax = catdrink * plannedAmount.toDouble() * 0.01,
                                    cat4atractionsMax = catatractions * plannedAmount.toDouble() * 0.01,
                                    cat5planeMax = catplane * plannedAmount.toDouble() * 0.01,
                                    cat6transportMax = cattransport * plannedAmount.toDouble() * 0.01,
                                    tripUsers = personsUser_In_Trip_inCreate,
                                    expenses = listOf(),
                                    historyReturns = listOf()
                                )
                            )
                            showADD = true
                            navController.popBackStack()
                        } else {
                            showADDError = true
                        }
                    },
                    drawable = R.drawable.img_add_user,
                    drawable2 = Icons.Filled.Check
                )
            }
        },
        modifier = Modifier.background(color = Color(0xff181f36))
    ) {
        Column(
            modifier = Modifier
                .background(Color(24, 31, 54))
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,

            )
        {
            if (showADDError) {
                Toast.makeText(
                    LocalContext.current, stringResource(R.string.toastNull),
                    Toast.LENGTH_SHORT
                ).show()
                showADDError = false
            }
            if (showADD) {
            Toast.makeText(
                LocalContext.current, stringResource(R.string.toastCorectADDTrip),
                Toast.LENGTH_SHORT
            ).show()
            showADD = false
        }
            Column(
                modifier = Modifier
                    .padding(40.dp)
                    .background(Color(24, 31, 54))
                    .fillMaxSize(),
                horizontalAlignment = Alignment.Start,

                ) {

//                trip name
                Text(
                    text = stringResource(R.string.nazwa),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(
                        Font(R.font.century_gothic)
                    ),
                )
                Spacer(modifier = Modifier.height(15.dp))
                TextField(
                    value = tripName,
                    onValueChange = { tripName = it },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .border(
                            2.dp, Color(89, 128, 255), RoundedCornerShape(10)
                        )
                        .background(Color(217, 217, 217), RoundedCornerShape(10))
                        .heightIn(min = 56.dp),

                    )

//                trip description
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(R.string.opis),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(
                        Font(R.font.century_gothic)
                    ),
                )
                Spacer(modifier = Modifier.height(15.dp))
                TextField(
                    value = tripDescription,
                    onValueChange = { tripDescription = it },
                    modifier = Modifier
                        .border(2.dp, Color(89, 128, 255), RoundedCornerShape(10))
                        .background(Color(217, 217, 217), RoundedCornerShape(10))
                        .heightIn(min = 56.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )

//                trip planned amount
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(R.string.planowana_kwota),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(
                        Font(R.font.century_gothic)
                    ),
                )
                Spacer(modifier = Modifier.height(15.dp))
                TextField(
                    value = plannedAmount,
                    onValueChange = { plannedAmount = it },
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .border(2.dp, Color(89, 128, 255), RoundedCornerShape(10))
                        .background(Color(217, 217, 217), RoundedCornerShape(10))
                        .heightIn(min = 56.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                )

//                planned number of days
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(R.string.ilosc_dni),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(
                        Font(R.font.century_gothic)
                    ),
                )
                Spacer(modifier = Modifier.height(15.dp))
                TextField(
                    value = numberOfDays,
                    onValueChange = { numberOfDays = it },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .border(2.dp, Color(89, 128, 255), RoundedCornerShape(10))
                        .background(Color(217, 217, 217), RoundedCornerShape(10))
                        .heightIn(min = 56.dp),
                )
                Spacer(modifier = Modifier.height(15.dp))
                PromptButton(
                    label = R.string.kategorie_przycisk,
                    onClick = {
                        expandedcat = !expandedcat
                    }
                )
                Spacer(modifier = Modifier.height(15.dp))
                if (expandedcat) {
                    Categories()
                }
            }

        }
    }
}


@Composable
fun Categories() {

    var catfoodtemp by remember { mutableStateOf(catfood.toString()) }
    var catsleeptemp by remember { mutableStateOf(catsleep.toString()) }
    var catdrinktemp by remember { mutableStateOf(catdrink.toString()) }
    var catatractionstemp by remember { mutableStateOf(catatractions.toString()) }
    var catplanetemp by remember { mutableStateOf(catplane.toString()) }
    var cattransporttemp by remember { mutableStateOf(cattransport.toString()) }

    Column(
        modifier = Modifier
            .background(Color(24, 31, 54))
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,

        ) {
        Text(
            text = stringResource(R.string.kategorie),
            color = Color.White,
            fontSize = 25.sp,
            fontFamily = FontFamily(
                Font(R.font.century_gothic)
            ),
        )
        Spacer(modifier = Modifier.height(15.dp))
//                food
        Text(
            text = stringResource(R.string.cat_jedzenie),
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = FontFamily(
                Font(R.font.century_gothic)
            ),
        )
        Spacer(modifier = Modifier.height(15.dp))
        TextField(
            value = catfoodtemp,
            onValueChange = { catfoodtemp = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .width(80.dp)
                .border(
                    2.dp, Color(89, 128, 255), RoundedCornerShape(10)
                )
                .background(Color(217, 217, 217), RoundedCornerShape(10))
                .heightIn(min = 56.dp),

            )

//                sleep
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.cat_spanie),
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = FontFamily(
                Font(R.font.century_gothic)
            ),
        )
        Spacer(modifier = Modifier.height(15.dp))
        TextField(
            value = catsleeptemp,
            onValueChange = { catsleeptemp = it },
            modifier = Modifier
                .width(80.dp)
                .border(2.dp, Color(89, 128, 255), RoundedCornerShape(10))
                .background(Color(217, 217, 217), RoundedCornerShape(10))
                .heightIn(min = 56.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )

//                drink
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.cat_napoje),
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = FontFamily(
                Font(R.font.century_gothic)
            ),
        )
        Spacer(modifier = Modifier.height(15.dp))
        TextField(
            value = catdrinktemp,
            onValueChange = { catdrinktemp = it },
            modifier = Modifier
                .width(80.dp)
                .border(2.dp, Color(89, 128, 255), RoundedCornerShape(10))
                .background(Color(217, 217, 217), RoundedCornerShape(10))
                .heightIn(min = 56.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )

//                atractions
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.cat_atrakcje),
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = FontFamily(
                Font(R.font.century_gothic)
            ),
        )
        Spacer(modifier = Modifier.height(15.dp))
        TextField(
            value = catatractionstemp,
            onValueChange = { catatractionstemp = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .width(80.dp)
                .border(2.dp, Color(89, 128, 255), RoundedCornerShape(10))
                .background(Color(217, 217, 217), RoundedCornerShape(10))
                .heightIn(min = 56.dp),
        )


//                plane
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.cat_samolot),
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = FontFamily(
                Font(R.font.century_gothic)
            ),
        )
        Spacer(modifier = Modifier.height(15.dp))
        TextField(
            value = catplanetemp,
            onValueChange = { catplanetemp = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .width(80.dp)
                .border(2.dp, Color(89, 128, 255), RoundedCornerShape(10))
                .background(Color(217, 217, 217), RoundedCornerShape(10))
                .heightIn(min = 56.dp),
        )

//                transport
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.cat_transport),
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = FontFamily(
                Font(R.font.century_gothic)
            ),
        )
        Spacer(modifier = Modifier.height(15.dp))
        TextField(
            value = cattransporttemp,
            onValueChange = { cattransporttemp = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .width(80.dp)
                .border(2.dp, Color(89, 128, 255), RoundedCornerShape(10))
                .background(Color(217, 217, 217), RoundedCornerShape(10))
                .heightIn(min = 56.dp),
        )

        Spacer(modifier = Modifier.height(50.dp))

    }


    if (!catfoodtemp.isNullOrEmpty()) catfood = catfoodtemp.toDouble()
    if (!catsleeptemp.isNullOrEmpty()) catsleep = catsleeptemp.toDouble()
    if (!catdrinktemp.isNullOrEmpty()) catdrink = catdrinktemp.toDouble()
    if (!catatractionstemp.isNullOrEmpty()) catatractions = catatractionstemp.toDouble()
    if (!catplanetemp.isNullOrEmpty()) catplane = catplanetemp.toDouble()
    if (!cattransporttemp.isNullOrEmpty()) cattransport = cattransporttemp.toDouble()


}


@Preview
@Composable
fun AddTripPreview() {
    AddTrip()
}
