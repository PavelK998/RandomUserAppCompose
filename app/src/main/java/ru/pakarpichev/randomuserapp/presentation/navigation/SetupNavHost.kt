package ru.pakarpichev.randomuserapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.pakarpichev.randomuserapp.presentation.screens.extendedInfoScreen.ExtendedIndoScreen
import ru.pakarpichev.randomuserapp.presentation.screens.mainScreen.MainScreen


sealed class Screens(val route: String){
    data object MainScreen: Screens(route = "main_screen")
    data object ExtendedInfoScreen: Screens(route = "extendedInfo_Screen")
}

@Composable
fun SetupNavController (navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.MainScreen.route){
        composable(Screens.MainScreen.route){
            MainScreen(navController = navController)
        }
        composable("${Screens.ExtendedInfoScreen.route}/{modelId}"){ backStackEntry ->
            ExtendedIndoScreen(navController = navController, backStackEntry.arguments?.getString("modelId"))
        }
    }
}