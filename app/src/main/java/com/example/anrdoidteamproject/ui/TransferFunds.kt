package com.example.anrdoidteamproject.ui

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
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

var value: String = "0.0"
var selectedPerson: String = ""

@SuppressLint("UnrememberedMutableState")
@Composable
fun transferFunds(
) {
    var valuetemp = mutableStateOf(value.toString())


    Column(
        modifier = Modifier
            .padding(30.dp)
            .background(Color(24, 31, 54))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

        )
    {

        TextFieldWithLabel1(
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            label = R.string.kwota,
            valuetemp
        )

        Text(
            text = stringResource(R.string.znajomi),
            color = Color.White,
            fontSize = 30.sp,
        )
        Spacer(modifier = Modifier.height(15.dp))


        Divider(color = Color.White, thickness = 2.dp)
        Preview_MultipleRadioButtons()


    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TransferFunds(
    navController: NavController = rememberNavController(),
    userInfoButtonOnClick: () -> Unit = {},
    homeButtonOnClick: () -> Unit = {},
    settingsButtonOnClick: () -> Unit = {},
) {

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
        topBar = { topBar(message = stringResource(R.string.zwroc_koszty)) },
        floatingActionButton = {
            ConfirmButton(confirmOnClick = {

                try {
                    value.toDouble()
                }
                catch (e: NumberFormatException) {value= "0.0"
                }

                if (value.toString().isNotEmpty() && value.toDouble() != 0.0 && selectedPerson != "") {

                    val tripRef = DatabaseConnection.db.getReference("trips")

                    var TransferTemp: ArrayList<TransferMoney> =ArrayList()

                    var ex: TransferMoney = TransferMoney(Firebase.auth.currentUser?.email.toString(),
                        selectedPerson, value.toDouble())

                    TransferTemp = historyReturns


                    tripRef.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            if (value.toDouble() != 0.0) {

                                TransferTemp.add(ex)
                                Log.d("eeeee", selectedPerson.toString())
                                for (user in tripUsers) {
                                    if (Firebase.auth.currentUser?.email.toString() == user.id) {
                                        //Log.d("eeeee", user.balance.toString())
                                        user.balance = user.balance + value.toDouble()
                                    }
                                }
                                for (user in tripUsers) {
                                    if (selectedPerson == user.id) {
                                        Log.d("eeeee", user.balance.toString())
                                        user.balance = user.balance - value.toDouble()
                                    }
                                }

                                tripRef.child(tripID.toString()).child("tripUsers").setValue(
                                    tripUsers
                                )
                                tripRef.child(tripID.toString()).child("historyReturns").setValue(
                                    TransferTemp
                                )


                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            TODO("Not yet implemented")
                        }
                    })

                    //Start
                    var TripGIT: Trip = Trip()
                    val tripsRef = DatabaseConnection.db.getReference("trips")
                    tripsRef.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            isLoading = false
                            for (childSnapshot in snapshot.children) {
                                Log.d("eeee", "${childSnapshot.key}")
                                Log.d("eeee", "${tripID}")

                                val trip = childSnapshot.getValue(Trip::class.java)
                                if (trip != null) {
                                    if (childSnapshot.key == tripID) {
                                        TripGIT = trip
                                        Log.d("eeee", "${tripID}")
                                        Log.d("eeee", trip.tripName)
                                        Log.d("eeee", TripGIT.tripName)
                                    }

                                }
                                selectedPerson = ""
                                value= "0.0"
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            TODO("Not yet implemented")
                        }
                    })
                    //END


//                    jak sie uda to:

                    if (!isLoading) {
                        transferData(TripGIT, tripID)
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
                LocalContext.current, stringResource(R.string.toastCorectADDReturn),
                Toast.LENGTH_SHORT
            ).show()
            showADD = false
        }

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

//@Preview
//@Composable
//fun TransferFundsPreview() {
//    AppScreens.TransferFunds()
//}


@Composable
fun TextFieldWithLabel1(
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    @StringRes label: Int,
    fieldValue: MutableState<String> = mutableStateOf("")
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        label = {
            Text(
                stringResource(label),
                color = Color.White,
            )
        },
        value = text,
        onValueChange = { newText ->
            text = newText
            fieldValue.value = text.text
        },
        keyboardOptions = keyboardOptions,
        textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
    )
    if (fieldValue.value.toString().isNotEmpty())
        value = fieldValue.value.toString()
    else value = "0.0"

}


@Preview
@Composable
fun Preview_MultipleRadioButtons() {

    val selectedValue = remember { mutableStateOf(selectedPerson) }

    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }


    val itemstemp= tripUsers.toMutableList()
    var user = User_in_trip()
    for (item in itemstemp){
        if (item.id==Firebase.auth.currentUser?.email.toString()){
            user=item
        }
    }
    itemstemp.remove(user)
    val items = itemstemp
    Column(Modifier.padding(8.dp)) {
//        Text(text = "Selected value: ${selectedValue.value.ifEmpty { "NONE" }}")
        items.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .selectable(
                        selected = isSelectedItem(item.id),
                        onClick = {
                            onChangeState(item.id)
                            selectedPerson = item.id
                        },
                        role = Role.RadioButton
                    )
                    .padding(8.dp)
            ) {


                Column(
                    modifier = Modifier
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = isSelectedItem(item.id),
                            onClick = null,
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color.White,
                                unselectedColor = Color.White,
                                disabledColor = Color.LightGray
                            )
                        )
                        Spacer(modifier = Modifier.width(25.dp))
                        Text(
                            text = item.id,
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
        }
    }
}
