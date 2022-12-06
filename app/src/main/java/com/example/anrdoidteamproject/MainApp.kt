package com.example.anrdoidteamproject

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.anrdoidteamproject.ui.FriendsList
import com.example.anrdoidteamproject.ui.TripsList

enum class AppScreens() {
    AddCategory,
    AddExpense,
    AddFriend,
    AddTrip,
    Balance,
    ChooseFriends,
    EditPayment,
    FriendsList,
    History,
    LogIn,
    MenuPayment,
    Register,
    Settings,
    Stats,
    TransferFunds,
    TripsList,
    UserInfo
}

@Composable
fun MainApp(modifier: Modifier){
    modifier.background(color = Color(0xff181f36))
    FriendsList()
}