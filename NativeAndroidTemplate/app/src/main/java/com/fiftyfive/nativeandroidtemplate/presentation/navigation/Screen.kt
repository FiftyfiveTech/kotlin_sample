package com.fiftyfive.nativeandroidtemplate.presentation.navigation

/**
 *  Sealed class for all destination screens
 */
sealed class Screen(val route:String){
    object HomeScreen : Screen(route = "home_screen")
    object DetailScreen : Screen(route = "detail_screen/{id}")
}
