package com.example.anrdoidteamproject


import android.util.Log
import android.view.Menu
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.anrdoidteamproject.ui.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

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
    homeButtonOnClick: () -> Unit = { navController.navigateSingleTopTo(AppScreens.TripsList.name) },
    userInfoButtonOnClick: () -> Unit = { navController.navigateSingleTopTo(AppScreens.UserInfo.name) },
    settingsButtonOnClick: () -> Unit = { navController.navigateSingleTopTo(AppScreens.Settings.name) },
    friendButtonOnClick: () -> Unit = { navController.navigateSingleTopTo(AppScreens.FriendsList.name) },
    addtripButtonOnClick: () -> Unit = { navController.navigateSingleTopTo(AppScreens.AddTrip.name) },
    addFriendsToTrip: () -> Unit = { navController.navigateSingleTopTo(AppScreens.ChooseFriends.name) },
    addFriends: () -> Unit = { navController.navigateSingleTopTo(AppScreens.AddFriend.name) },
    addExpense: () -> Unit = { navController.navigateSingleTopTo(AppScreens.AddExpense.name) },
    addCategory: () -> Unit = { navController.navigateSingleTopTo(AppScreens.AddCategory.name) },
) {
    NavHost(
        navController = navController,
        startDestination = AppScreens.LogIn.name
    ) {
        composable(AppScreens.LogIn.name) {
            LogIn(
                registerButtonOnClick = { navController.navigate(AppScreens.Register.name) },
                navController = navController
            )
//          disable go back phone button
            BackHandler(true) {
//
            }
        }
        composable(AppScreens.Register.name) {
            Register(navController)
//          disable go back phone button
            BackHandler(true) {
//
            }
        }
        composable(AppScreens.TripsList.name) {
            TripsList(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick,
                addtripButtonOnClick = addtripButtonOnClick
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
                settingsButtonOnClick = settingsButtonOnClick,
                addFriendsToTrip = addFriendsToTrip
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
                settingsButtonOnClick = settingsButtonOnClick,
                addFriends = addFriends
            )
        }
        composable(AppScreens.History.name) {
            History(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        }
        composable(AppScreens.MenuPayment.name) {
            MenuPayment(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        }
        composable(AppScreens.Stats.name) {
            Stats(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick,
                addExpense = addExpense,
                addCategory = addCategory,
                tripName = " "
            )
        }
        composable(AppScreens.TransferFunds.name) {
            TransferFunds(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick
            )
        }
        composable(AppScreens.UserInfo.name) {
            UserInfo(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick,
                friendButtonOnClick = friendButtonOnClick
            )
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }





