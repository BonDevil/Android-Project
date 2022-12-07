package com.example.anrdoidteamproject


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.anrdoidteamproject.ui.LogIn
import com.example.anrdoidteamproject.ui.Register
import com.example.anrdoidteamproject.ui.Settings
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
fun MainApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = AppScreens.LogIn.name
    ) {
        composable(AppScreens.LogIn.name){
            LogIn(
                registerButtonOnClick = {navController.navigate(AppScreens.Register.name)},
                logInButtonOnClick = {navController.navigate(AppScreens.TripsList.name)}
            )
        }
        composable(AppScreens.Register.name){
            Register()
        }
        composable(AppScreens.TripsList.name){
            TripsList(
                userInfoButtonOnClick = {navController.navigate(AppScreens.UserInfo.name)},
                homeButtonOnClick = {navController.navigate(AppScreens.TripsList.name)},
                settingsButtonOnClick = {navController.navigate(AppScreens.Settings.name)},
            )
        }
        composable(AppScreens.Settings.name){
            Settings()
        }

    }
}