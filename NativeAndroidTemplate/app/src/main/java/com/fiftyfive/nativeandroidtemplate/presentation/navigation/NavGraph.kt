package com.fiftyfive.nativeandroidtemplate.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.fiftyfive.nativeandroidtemplate.presentation.screens.DetailScreen
import com.fiftyfive.nativeandroidtemplate.presentation.screens.HomeScreen

@Composable
fun SetNavGraph(
    navController: NavHostController
){
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route){
        composable(Screen.HomeScreen.route){
            HomeScreen(navController)
        }
        composable(
            Screen.DetailScreen.route, listOf(
            navArgument("id"){
                type = NavType.StringType
            }
        )){
            DetailScreen(
                it.arguments!!.getString("id").toString()
            )
        }
    }
}