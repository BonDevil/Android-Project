package com.example.anrdoidteamproject.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
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
import com.example.anrdoidteamproject.ui.theme.bottomBar
import com.example.anrdoidteamproject.ui.theme.topBar


@Composable
fun EditPayment(
    userInfoButtonOnClick: () -> Unit = {},
    homeButtonOnClick: () -> Unit = {},
    settingsButtonOnClick: () -> Unit = {}
) {

    Scaffold(
        bottomBar = {
            bottomBar(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        },
        topBar = { topBar(message = stringResource(R.string.edytuj_wydatek)) },
        modifier = Modifier.background(color = Color(0xff181f36))

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color(0xff181f36))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.925f)
                    .background(color = Color(0xff181f36))
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .background(Color(24, 31, 54))
                            .fillMaxHeight()
                            .fillMaxWidth(0.85f),
                        horizontalAlignment = Alignment.Start,

                        )
                    {
                        Text(
                            text = stringResource(R.string.nazwa),
                            color = Color.White,
                            fontSize = 20.sp,
                            fontFamily = FontFamily(
                                Font(R.font.century_gothic)
                            ),
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        TextField(
                            value = "",
                            onValueChange = {},
//            KeyboardOptions(
//                keyboardType = KeyboardType.Text,
//                imeAction = ImeAction.Next
//            ),
                            modifier = Modifier
                                .border(2.dp, Color(89, 128, 255), RoundedCornerShape(10))

                                .background(Color(217, 217, 217), RoundedCornerShape(10))
                                .heightIn(min = 20.dp),

                            )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = stringResource(R.string.kategoria),
                            color = Color.White,
                            fontSize = 20.sp,
                            fontFamily = FontFamily(
                                Font(R.font.century_gothic)
                            ),
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        TextField(
                            value = "",
                            onValueChange = {},
                            modifier = Modifier
                                .border(2.dp, Color(89, 128, 255), RoundedCornerShape(10))
                                .background(Color(217, 217, 217), RoundedCornerShape(10))
                                .heightIn(min = 20.dp),
//
//            KeyboardOptions(
//                keyboardType = KeyboardType.Text,
//                imeAction = ImeAction.Next
//            ),
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = stringResource(R.string.kwota),
                            color = Color.White,
                            fontSize = 20.sp,
                            fontFamily = FontFamily(
                                Font(R.font.century_gothic)
                            ),
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        TextField(
                            value = "",
                            onValueChange = {},
//            KeyboardOptions(
//                keyboardType = KeyboardType.Text,
//                imeAction = ImeAction.Next
//            ),
                            modifier = Modifier
                                .fillMaxWidth(0.6f)
                                .border(2.dp, Color(89, 128, 255), RoundedCornerShape(10))
                                .background(Color(217, 217, 217), RoundedCornerShape(10))
                                .heightIn(min = 20.dp),

                            )
                        Spacer(modifier = Modifier.height(20.dp))
                        /*TextFieldWithLabel(

                        KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        label = R.string.nazwa,

                        )

                    Spacer(modifier = Modifier.height(15.dp))
                    DropdownCategories()

                    Spacer(modifier = Modifier.height(15.dp))


                    TextFieldWithLabel(
                        KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        label = R.string.kwota,
        //            modifierLocalOf {Modifier} = Modifier
        //                .background(Color(217,217,217), RoundedCornerShape(10))
        //                .heightIn(min = 56.dp),
                    )
                    Spacer(modifier = Modifier.height(15.dp))*/


                        Text(
                            text = stringResource(R.string.znajomi),
                            color = Color.White,
                            fontSize = 20.sp,
                        )
                        Spacer(modifier = Modifier.height(15.dp))


                        Divider(color = Color.White, thickness = 2.dp)
                        Listpersons3(SampleData3.conversationSample)


                    }
                }
            }

        }

    }
}


@Preview
@Composable
fun EditPaymentPreview() {
EditPayment()
}