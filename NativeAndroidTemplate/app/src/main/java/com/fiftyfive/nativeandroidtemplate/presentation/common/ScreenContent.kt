package com.fiftyfive.nativeandroidtemplate.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.fiftyfive.nativeandroidtemplate.R
import com.fiftyfive.nativeandroidtemplate.presentation.navigation.SetNavGraph


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContent(navController: NavHostController) {
    var isDarkTheme by remember { mutableStateOf(true) }

    /**
     *  Method that is responsible for changing the theme
     */
//    AppThemeSetter(isDarkTheme = isDarkTheme) {

        Column {
            TopAppBar(
                title = {
                    Row {
                        Text(
                            text = stringResource(R.string.app_name),
                            modifier = Modifier.run {
                                weight(1f)
                                    .wrapContentHeight()
                                    .align(Alignment.CenterVertically)
                            }
                        )
                        Row(
                            modifier = Modifier.align(Alignment.CenterVertically)
                        ) {
                            Text(
                                stringResource(id = R.string.light_theme_text),
                                Modifier.align(Alignment.CenterVertically)
                            )
                            Switch(
                                checked = isDarkTheme,
                                onCheckedChange = {
                                    isDarkTheme = it
                                }
                                ,
                                modifier = Modifier.padding(8.dp)
                            )
                            Text(
                                stringResource(id = R.string.dark_theme_text),
                                Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                })

            SetNavGraph(navController = navController)
        }
//    }
}
