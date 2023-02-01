package com.example.anrdoidteamproject.ui

import android.annotation.SuppressLint
import android.os.Build
import android.provider.ContactsContract.Data
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.anrdoidteamproject.AppScreens
import com.example.anrdoidteamproject.R
import com.example.anrdoidteamproject.businessLogic.*
import com.example.anrdoidteamproject.ui.theme.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

private var category = "food"
private var list: ArrayList<String> = ArrayList()
private var listUserInTrip: ArrayList<User_in_trip> = ArrayList()
private var selectedIndexG: Int = 0

@Composable
fun DropdownCategories() {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf(
        stringResource(R.string.cat_jedzenie),
        stringResource(R.string.cat_spanie),
        stringResource(R.string.cat_napoje),
        stringResource(R.string.cat_atrakcje),
        stringResource(R.string.cat_samolot),
        stringResource(R.string.cat_transport),
    )

    var selectedIndex by remember { mutableStateOf(0) }
    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart)
            .border(2.dp, Color(89, 128, 200), RoundedCornerShape(10))
    ) {
        Text(

            items[selectedIndex],
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .clickable(onClick = { expanded = true }),
            fontSize = 30.sp,
            color = Color.White,

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
                    Text(
                        text = s,
                        fontSize = 30.sp,
                    )
                }
            }
        }
    }
    category = items[selectedIndex].toString()
    selectedIndexG = selectedIndex
}

@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddExpense(
    userInfoButtonOnClick: () -> Unit = {},
    homeButtonOnClick: () -> Unit = {},
    settingsButtonOnClick: () -> Unit = {},
    navController: NavController = rememberNavController()

) {
    var expenseName = mutableStateOf("")
    var expenseSUM = mutableStateOf("")
    var showADDError by remember { mutableStateOf(false) }
    var showADD by remember { mutableStateOf(false) }
    var isLoadingAnimation by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }
    Scaffold(
        bottomBar = {
            bottomBar(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        },
        topBar = { topBar(message = stringResource(R.string.dodaj_wydatek)) },
        floatingActionButton = {
            ConfirmButton(confirmOnClick = {

                try {
                    expenseSUM.value.toDouble()
                }
                catch (e: NumberFormatException) {expenseSUM.value= ""
                }

                if (!expenseSUM.value.isNullOrEmpty() && !expenseName.value.isNullOrEmpty() && expenseSUM.value.toDouble() != 0.0 && !listUserInTrip.isEmpty()) {
                    val tripRef = DatabaseConnection.db.getReference("trips")


                    var expensesTemp: ArrayList<Expenditure>


                    expensesTemp = expenses

                    tripRef.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            if (expenseSUM.value.toDouble() != 0.0) {

                                var ex: Expenditure = Expenditure(
                                    paying_person = Firebase.auth.currentUser?.email.toString(),
                                    category = selectedIndexG,
                                    value = expenseSUM.value.toDouble(),
                                    name = expenseName.value.toString()
                                )

                                var size = list.size
                                var balanceChange = expenseSUM.value.toDouble() / size

                                for (usertemp in list) {
                                    for (user in tripUsers) {
                                        if (usertemp == user.id) {
                                            user.balance = user.balance - balanceChange
                                        }
                                    }
                                }

                                for (user in tripUsers) {
                                    if (Firebase.auth.currentUser?.email.toString() == user.id) {
                                        Log.d("eeeee", user.balance.toString())
                                        user.balance = user.balance + expenseSUM.value.toDouble()
                                    }
                                }
                                list.clear()




                                expensesTemp.add(ex)


                                tripRef.child(tripID.toString()).child("totalAmount")
                                    .setValue(TotalAmount + expenseSUM.value.toDouble())

                                val newTripRef = tripRef.child(tripID.toString()).child("expenses")
                                newTripRef.setValue(
                                    expensesTemp
                                )

                                tripRef.child(tripID.toString()).child("tripUsers").setValue(
                                    tripUsers
                                )


                            }

                            //Add to groups
                            when (selectedIndexG) {
                                0 -> {
                                    tripRef.child(tripID.toString()).child("cat1food").setValue(
                                        cat1food + expenseSUM.value.toDouble()
                                    )
                                    TotalAmount += expenseSUM.value.toDouble()
                                    cat1food += expenseSUM.value.toDouble()


                                    transferData(
                                        Trip(
                                            tripName,
                                            plannedAmount,
                                            numberOfDays,
                                            cat1foodMax,
                                            cat2sleepMax,
                                            cat3drinkMax,
                                            cat4atractionsMax,
                                            cat5planeMax,
                                            cat6transportMax,
                                            cat1food,
                                            cat2sleep,
                                            cat3drink,
                                            cat4atractions,
                                            cat5plane,
                                            cat6transport,
                                            TotalAmount,
                                            expenses,
                                            tripUsers,
                                            historyReturns
                                        ), tripID
                                    )
                                    isLoading = false

                                }
                                1 -> {
                                    tripRef.child(tripID.toString()).child("cat2sleep").setValue(
                                        cat2sleep + expenseSUM.value.toDouble()
                                    )
                                    TotalAmount += expenseSUM.value.toDouble()
                                    cat2sleep += expenseSUM.value.toDouble()

                                    transferData(
                                        Trip(
                                            tripName,
                                            plannedAmount,
                                            numberOfDays,
                                            cat1foodMax,
                                            cat2sleepMax,
                                            cat3drinkMax,
                                            cat4atractionsMax,
                                            cat5planeMax,
                                            cat6transportMax,
                                            cat1food,
                                            cat2sleep,
                                            cat3drink,
                                            cat4atractions,
                                            cat5plane,
                                            cat6transport,
                                            TotalAmount,
                                            expenses,
                                            tripUsers,
                                            historyReturns
                                        ), tripID
                                    )
                                    isLoading = false

                                }
                                2 -> {
                                    tripRef.child(tripID.toString()).child("cat3drink").setValue(
                                        cat3drink + expenseSUM.value.toDouble()
                                    )
                                    TotalAmount += expenseSUM.value.toDouble()
                                    cat3drink += expenseSUM.value.toDouble()

                                    transferData(
                                        Trip(
                                            tripName,
                                            plannedAmount,
                                            numberOfDays,
                                            cat1foodMax,
                                            cat2sleepMax,
                                            cat3drinkMax,
                                            cat4atractionsMax,
                                            cat5planeMax,
                                            cat6transportMax,
                                            cat1food,
                                            cat2sleep,
                                            cat3drink,
                                            cat4atractions,
                                            cat5plane,
                                            cat6transport,
                                            TotalAmount,
                                            expenses,
                                            tripUsers,
                                            historyReturns
                                        ), tripID
                                    )
                                    isLoading = false

                                }
                                3 -> {
                                    tripRef.child(tripID.toString()).child("cat4atractions")
                                        .setValue(
                                            cat4atractions + expenseSUM.value.toDouble()
                                        )

                                    TotalAmount += expenseSUM.value.toDouble()
                                    cat4atractions += expenseSUM.value.toDouble()
                                    transferData(
                                        Trip(
                                            tripName,
                                            plannedAmount,
                                            numberOfDays,
                                            cat1foodMax,
                                            cat2sleepMax,
                                            cat3drinkMax,
                                            cat4atractionsMax,
                                            cat5planeMax,
                                            cat6transportMax,
                                            cat1food,
                                            cat2sleep,
                                            cat3drink,
                                            cat4atractions,
                                            cat5plane,
                                            cat6transport,
                                            TotalAmount,
                                            expenses,
                                            tripUsers,
                                            historyReturns
                                        ), tripID
                                    )
                                    isLoading = false

                                }
                                4 -> {
                                    tripRef.child(tripID.toString()).child("cat5plane").setValue(
                                        cat5plane + expenseSUM.value.toDouble()
                                    )

                                    TotalAmount += expenseSUM.value.toDouble()
                                    cat5plane += expenseSUM.value.toDouble()
                                    transferData(
                                        Trip(
                                            tripName,
                                            plannedAmount,
                                            numberOfDays,
                                            cat1foodMax,
                                            cat2sleepMax,
                                            cat3drinkMax,
                                            cat4atractionsMax,
                                            cat5planeMax,
                                            cat6transportMax,
                                            cat1food,
                                            cat2sleep,
                                            cat3drink,
                                            cat4atractions,
                                            cat5plane,
                                            cat6transport,
                                            TotalAmount,
                                            expenses,
                                            tripUsers,
                                            historyReturns
                                        ), tripID
                                    )
                                    isLoading = false

                                }
                                5 -> {
                                    tripRef.child(tripID.toString()).child("cat6transport")
                                        .setValue(
                                            cat6transport + expenseSUM.value.toDouble()
                                        )

                                    TotalAmount += expenseSUM.value.toDouble()
                                    cat6transport += expenseSUM.value.toDouble()
                                    transferData(
                                        Trip(
                                            tripName,
                                            plannedAmount,
                                            numberOfDays,
                                            cat1foodMax,
                                            cat2sleepMax,
                                            cat3drinkMax,
                                            cat4atractionsMax,
                                            cat5planeMax,
                                            cat6transportMax,
                                            cat1food,
                                            cat2sleep,
                                            cat3drink,
                                            cat4atractions,
                                            cat5plane,
                                            cat6transport,
                                            TotalAmount,
                                            expenses,
                                            tripUsers,
                                            historyReturns
                                        ), tripID
                                    )
                                    isLoading = false

                                }

                            }



                            selectedIndexG = 100
                            expenseSUM.value = "0"


                        }


                        override fun onCancelled(error: DatabaseError) {
                            TODO("Not yet implemented")
                        }

                    })
                    if (!isLoading) {
                        listUserInTrip.clear()
                        list.clear()

                    } else {
                        isLoadingAnimation = true
                    }


                } else {
                    showADDError = true
                }
            }
            )
        },
        modifier = Modifier.background(color = Color(0xff181f36))

    ) {
        if (!isLoading) {
            navController.popBackStack()
            navController.popBackStack()
            navController.navigate(
                AppScreens.Stats.name
            )
            showADD = true
            isLoading = true
        }
        if (isLoading) LoadingAnimation()
        if (showADDError) {
            Toast.makeText(
                LocalContext.current, stringResource(R.string.toastNull),
                Toast.LENGTH_SHORT
            ).show()
            showADDError = false
        }
        if (showADD) {
            Toast.makeText(
                LocalContext.current, stringResource(R.string.toastCorectADDExpense),
                Toast.LENGTH_SHORT
            ).show()
            showADD = false
        }
        Column(
            modifier = Modifier
                .background(Color(24, 31, 54))
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxHeight()
                    .fillMaxWidth(0.85f),
                horizontalAlignment = Alignment.Start,

                )
            {
                Spacer(modifier = Modifier.height(10.dp))
                TextFieldWithLabel(
                    KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    label = R.string.nazwa,
                    expenseName
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(R.string.kategoria),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(
                        Font(R.font.century_gothic)
                    ),
                )
                Spacer(modifier = Modifier.height(10.dp))
                DropdownCategories()
                Spacer(modifier = Modifier.height(20.dp))

                TextFieldWithLabel(
                    KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    label = R.string.kwota,
                    expenseSUM
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = stringResource(R.string.znajomi),
                    color = Color.White,
                    fontSize = 20.sp,
                )
                Spacer(modifier = Modifier.height(15.dp))

                Divider(color = Color.White, thickness = 2.dp)
                Listpersons3(tripUsers)
            }
        }
    }
}


@Composable
fun PersonCard3(user: User_in_trip) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row() {
            CheckBoxDemo(user.id)
            Text(
                text = user.id,
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
fun Listpersons3(user: List<User_in_trip>) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 40.dp)

    ) {
        user.map { item { PersonCard3(it) } }
    }
}


@Composable
fun CheckBoxDemo(email: String) {
    val checkedState = rememberSaveable { mutableStateOf(false) }
    Checkbox(
        checked = checkedState.value,
        onCheckedChange = {
            checkedState.value = it
            if (checkedState.value) {
                list.add(email)
                listUserInTrip.add(User_in_trip(email))
            } else {
                list.remove(email)
                listUserInTrip.remove(
                    User_in_trip(email)
                )
            }

        }
    )
}