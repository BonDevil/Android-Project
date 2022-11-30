package com.example.anrdoidteamproject

import androidx.compose.runtime.Composable
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
fun MainApp(){
    FriendsList()
}