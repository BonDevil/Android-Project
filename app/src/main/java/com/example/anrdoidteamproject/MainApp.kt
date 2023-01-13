package com.example.anrdoidteamproject


import android.os.Build
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.anrdoidteamproject.businessLogic.DatabaseConnection
import com.example.anrdoidteamproject.businessLogic.User
import com.example.anrdoidteamproject.ui.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import java.util.*

enum class AppScreens() {
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
    UserInfo,
    Invitations,
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    homeButtonOnClick: () -> Unit = { navController.navigateSingleTopTo(AppScreens.TripsList.name) },
    userInfoButtonOnClick: () -> Unit = { navController.navigateSingleTopTo(AppScreens.UserInfo.name) },
    settingsButtonOnClick: () -> Unit = { navController.navigateSingleTopTo(AppScreens.Settings.name) },
    friendButtonOnClick: () -> Unit = { navController.navigateSingleTopTo(AppScreens.FriendsList.name) },
    addTripButtonOnClick: () -> Unit = { navController.navigateSingleTopTo(AppScreens.AddTrip.name) },
    addFriendsToTrip: () -> Unit = { navController.navigateSingleTopTo(AppScreens.ChooseFriends.name) },
    addFriends: () -> Unit = { navController.navigateSingleTopTo(AppScreens.AddFriend.name) },
    addExpense: () -> Unit = { navController.navigateSingleTopTo(AppScreens.AddExpense.name) },
    statsButtonOnClick: () -> Unit = { navController.navigateSingleTopTo(AppScreens.Stats.name) },
    invitationButton: () -> Unit = { navController.navigateSingleTopTo(AppScreens.Invitations.name) },
    balanceButton: () -> Unit = { navController.navigateSingleTopTo(AppScreens.Balance.name) },
    historyButton: () -> Unit = { navController.navigateSingleTopTo(AppScreens.History.name) },
    transferFundsButton: () -> Unit = { navController.navigateSingleTopTo(AppScreens.TransferFunds.name) },

    ) {

    var user by remember { mutableStateOf(Firebase.auth.currentUser) }
    var naw = String()
    if (user != null) {
        naw = AppScreens.TripsList.name
    } else {
        naw = AppScreens.LogIn.name
    }
    //default language is Polish
    com.example.anrdoidteamproject.ui.translator(lan = "pl")
    NavHost(
        navController = navController,
        startDestination = naw
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
                addtripButtonOnClick = addTripButtonOnClick,
                statsButtonOnClick = statsButtonOnClick
            )
//          disable go back phone button
            BackHandler(true) {
//
            }
        }
        composable(AppScreens.Settings.name) {
            Settings(
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
                settingsButtonOnClick = settingsButtonOnClick,
                topbarButton = historyButton,
                transferFunds = transferFundsButton
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
                addFriends = addFriends,
                invitationButton = invitationButton
            )
        }
        composable(AppScreens.History.name) {
            History(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick,
                topbarButton = balanceButton
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
                bilansButton = balanceButton,
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
                friendButtonOnClick = friendButtonOnClick,
                navController = navController
            )
        }
        composable(AppScreens.Invitations.name) {
            InvitationsList(
                userInfoButtonOnClick = userInfoButtonOnClick,
                homeButtonOnClick = homeButtonOnClick,
                settingsButtonOnClick = settingsButtonOnClick,
            )
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }

@Composable
fun translator(lan: String) {
    val ct = LocalContext.current
    val locale = Locale(lan)
    Locale.setDefault(locale)
    val resources = ct.resources
    val configuration = resources.configuration
    configuration.locale = locale
    resources.updateConfiguration(configuration, resources.displayMetrics)
}





