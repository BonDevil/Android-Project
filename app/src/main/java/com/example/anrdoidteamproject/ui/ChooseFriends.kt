package com.example.anrdoidteamproject.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anrdoidteamproject.R
import com.example.anrdoidteamproject.businessLogic.*
import com.example.anrdoidteamproject.ui.theme.ConfirmButton
import com.example.anrdoidteamproject.ui.theme.bottomBar
import com.example.anrdoidteamproject.ui.theme.topBar

var choosenFriendsMails: ArrayList<String> = ArrayList()
var listUserInTrip: ArrayList<User_in_trip> = ArrayList()


@Composable
fun PersonCard2(user: User) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CheckBoxDemo2(user.email)
            Text(
                text = user.firstName + " " + user.lastName,
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
fun listChooseFriends(users: List<User>) {
    LazyColumn {
        users.map { item { PersonCard2(it) } }
    }
}

@Composable
fun CheckBoxDemo2(email: String) {
    val checkedState = rememberSaveable { mutableStateOf(false) }
    Checkbox(
        checked = checkedState.value,
        onCheckedChange = {
            checkedState.value = it
            if (checkedState.value) {
                choosenFriendsMails.add(email)
                listUserInTrip.add(User_in_trip(email))
            } else {
                choosenFriendsMails.remove(email)
                listUserInTrip.remove(
                    User_in_trip(email)
                )
            }

        }
    )
}

@Composable
fun ChooseFriends(
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
        topBar = { topBar(message = stringResource(R.string.dodaj_znajomych)) },
        floatingActionButton = {
            ConfirmButton(confirmOnClick = {
                persons2 = choosenFriendsMails
                personsUser_In_Trip_inCreate = listUserInTrip

            }
            )
        },
        modifier = Modifier.background(color = Color(0xff181f36))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color(0xff181f36))
        ) {
            listChooseFriends(users = DatabaseConnection.friendList)
        }
    }
}




