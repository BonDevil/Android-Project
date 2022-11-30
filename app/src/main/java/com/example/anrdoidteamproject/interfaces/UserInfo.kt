package com.example.anrdoidteamproject.interfaces

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anrdoidteamproject.R

class UserInfo : BaseScreen() {


    @Composable
    open fun userlist(imie:String, Nazwisko:String, E_mail:String, Numer:String) {

        Column(
            modifier = Modifier
                .padding(20.dp)
                .background(Color(24, 31, 54))
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,

            )
        {

            Text(
                text = stringResource(R.string.imie),
                color = Color.White,
                fontSize = 30.sp,
            )
            Spacer(modifier = Modifier.height(15.dp))
            fText(fftekst = imie)
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.nazwisko),
                color = Color.White,
                fontSize = 30.sp,
            )
            Spacer(modifier = Modifier.height(15.dp))
            fText(fftekst = Nazwisko)
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.email),
                color = Color.White,
                fontSize = 30.sp,
            )
            Spacer(modifier = Modifier.height(15.dp))
            fText(fftekst = E_mail)
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.telefon),
                color = Color.White,
                fontSize = 30.sp,
            )
            Spacer(modifier = Modifier.height(15.dp))
            fText(fftekst = Numer)
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedButton( onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(89, 128, 255),
                disabledBackgroundColor = Color(70, 99, 255),
            )) {
                Text(text = stringResource(R.string.znajomi),
                    fontSize = 30.sp)

            }
        }
    }

    @Composable
    fun fText(fftekst:String){
        Text(
            text = fftekst,
            color = Color(89, 128, 255),
            fontSize = 30.sp,
        )
        Spacer(modifier = Modifier.height(15.dp))
    }



    @Composable
    override fun mainScreen() {
        Column() {
            topBar(message = stringResource(R.string.konto))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.925f)
                    .background(color = Color(0xff181f36))){
                userlist("Jan","Kowalski","Jak_Kowalski@gmail.com","123456789")
            }
            bottomBar()
        }
    }
}