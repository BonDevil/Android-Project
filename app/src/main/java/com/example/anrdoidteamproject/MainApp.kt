package com.example.anrdoidteamproject


import android.util.Log
import android.view.Menu
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
import com.example.anrdoidteamproject.ui.*

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
    navController: NavHostController = rememberNavController(),
    homeButtonOnClick: () -> Unit = { navController.navigate(AppScreens.TripsList.name)},
    userInfoButtonOnClick: () -> Unit = { navController.navigate(AppScreens.UserInfo.name) },
    settingsButtonOnClick: () -> Unit = { navController.navigate(AppScreens.Settings.name) }
){
    NavHost(
        navController = navController,
        startDestination = AppScreens.LogIn.name
    ) {
        composable(AppScreens.LogIn.name) {
            LogIn(
                registerButtonOnClick = { navController.navigate(AppScreens.Register.name) },
                logInButtonOnClick = { navController.navigate(AppScreens.TripsList.name) }
            )
        }
        composable(AppScreens.Register.name) {
            Register()
        }
        composable(AppScreens.TripsList.name) {
            TripsList(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        }
        composable(AppScreens.Settings.name) {
            Settings(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        }
        composable(AppScreens.AddCategory.name) {
            AddCategory(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        }
        composable(AppScreens.AddExpense.name) {
            AddExpense(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        }
        composable(AppScreens.AddFriend.name) {
            AddFriend(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        }
        composable(AppScreens.AddTrip.name) {
            AddTrip(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        }
        composable(AppScreens.Balance.name) {
            Balance(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        }
        composable(AppScreens.ChooseFriends.name) {
            ChooseFriends(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        }
        composable(AppScreens.EditPayment.name) {
            EditPayment(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        }
        composable(AppScreens.FriendsList.name) {
            FriendsList(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        }
        composable(AppScreens.History.name) {
            History(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        }
        composable(AppScreens.MenuPayment.name){
            MenuPayment(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        }
        composable(AppScreens.Stats.name){
            Stats(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        }
        composable(AppScreens.TransferFunds.name){
            TransferFunds(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        }
        composable(AppScreens.UserInfo.name){
            UserInfo(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        }
    }
}