package com.example.anrdoidteamproject.ui

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import com.example.anrdoidteamproject.ui.theme.*

var value:Double = 0.0

@SuppressLint("UnrememberedMutableState")
@Composable
fun transferFunds() {
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
    LazyColumn(
        contentPadding = PaddingValues(vertical = 40.dp)

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
fun TransferFunds(
    navController: NavController = rememberNavController(),
    userInfoButtonOnClick: () -> Unit = {},
    homeButtonOnClick: () -> Unit = {},
    settingsButtonOnClick: () -> Unit = {}
) {

    var showADDError by remember { mutableStateOf(false) }
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

                if (value.toString().isNotEmpty()&& value!=0.0){
            /*TODO*/
                //wartosc zwrotu value
                //value
                //osoba lub osoby ktore mają miec usuniety zysk z bilansu (lista osob w wycieczce i zmienic bilans)
                //(jakas zmienna albo lista)

                //osoba ktora zwraca koszty i ma miec polepszony bilans
                //paying_person = Firebase.auth.currentUser.hashCode()
                }

                else{
                    showADDError=true
                }

            }

            )
        },
        modifier = Modifier.background(color = Color(0xff181f36))

    ) {
        if (showADDError) {
            Toast.makeText(
                LocalContext.current,stringResource(R.string.toastNull) ,
                Toast.LENGTH_SHORT
            ).show()
            showADDError = false
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

@Preview
@Composable
fun TransferFundsPreview() {
    TransferFunds()
}



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
    value=fieldValue.value.toString().toDouble()
    else value=0.0

}

