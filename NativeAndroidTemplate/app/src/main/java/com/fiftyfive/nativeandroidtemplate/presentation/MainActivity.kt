package com.fiftyfive.nativeandroidtemplate.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fiftyfive.nativeandroidtemplate.presentation.common.ScreenContent
import com.fiftyfive.nativeandroidtemplate.presentation.theme.NativeAndroidTemplateTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Only Activity of the app
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // Controls the navigation of screen
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        /**
         * Creates a SplashScreen instance associated with this Activity
         */
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()

            NativeAndroidTemplateTheme {
                ScreenContent(navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NativeAndroidTemplateTheme {

    }
}