package com.example.anrdoidteamproject.interfaces

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anrdoidteamproject.R

class AddCategory : BaseScreen() {


    @Composable
    open fun category_valus() {

        Column(
            modifier = Modifier
                .padding(20.dp)
                .background(Color(24, 31, 54))
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,

            )
        {

            Text(
                text = stringResource(R.string.nazwa),
                color = Color.White,
                fontSize = 30.sp,
            )
            Spacer(modifier = Modifier.height(15.dp))
            SimpleTextField()
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(R.string.opis),
                color = Color.White,
                fontSize = 30.sp,
            )
            Spacer(modifier = Modifier.height(15.dp))
            SimpleTextField()
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(R.string.dodaj_ikone),
                color = Color.White,
                fontSize = 30.sp,
            )
            Spacer(modifier = Modifier.height(15.dp))
            /// TODO:  Dodac dodawanie ikony
            Spacer(modifier = Modifier.height(60.dp))
            Text(
                text = stringResource(R.string.wybierz_kolor),
                color = Color.White,
                fontSize = 30.sp,
            )
            Spacer(modifier = Modifier.height(60.dp))
            // TODO: Dodac wybieranie koloru 
            Spacer(modifier = Modifier.height(60.dp))

        }
    }


    @Composable
    fun SimpleTextField() {
        var text by remember { mutableStateOf(TextFieldValue("")) }

        TextField(
            value = text,
            onValueChange = { newText ->
                text = newText
            },

            textStyle = TextStyle(color = Color.White, fontSize = 30.sp)
        )
    }




    @Composable
    override fun mainScreen() {
        Column() {
            topBar(message = stringResource(R.string.dodaj_kategorie))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.925f)
                    .background(color = Color(0xff181f36))){
                    category_valus()
            }
            bottomBar()
        }
    }
}