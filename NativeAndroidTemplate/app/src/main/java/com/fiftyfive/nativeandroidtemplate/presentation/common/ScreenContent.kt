package com.fiftyfive.nativeandroidtemplate.presentation.common

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.fiftyfive.nativeandroidtemplate.R
import com.fiftyfive.nativeandroidtemplate.presentation.MainActivity
import com.fiftyfive.nativeandroidtemplate.presentation.navigation.SetNavGraph
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContent(navController: NavHostController) {
    var isDarkTheme by remember { mutableStateOf(true) }

    /**
     *  Method that is responsible for changing the theme
     */
    AppThemeSetter(isDarkTheme = isDarkTheme) {
        val context = LocalContext.current
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
                            },
                            fontSize = 16.sp
                        )
                        Row(
                            modifier = Modifier.align(Alignment.CenterVertically)
                        ) {
                            val configuration = Configuration(context.resources.configuration)

                            Text(
                                stringResource(id = R.string.english),
                                Modifier.align(Alignment.CenterVertically),
                                fontSize = 14.sp
                            )
                            val displayName = configuration.locales.get(0).toLanguageTag()
                            Switch(
                                displayName.equals("En", true), onCheckedChange = {
                                updateLanguage(context)
                            },
                                modifier = Modifier.padding(8.dp))
                            Text(
                                stringResource(id = R.string.french),
                                Modifier.align(Alignment.CenterVertically),
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                stringResource(id = R.string.light_theme_text),
                                Modifier.align(Alignment.CenterVertically),
                                fontSize = 14.sp
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
                                Modifier.align(Alignment.CenterVertically),
                                fontSize = 14.sp
                            )
                        }
                    }
                })

            SetNavGraph(navController = navController)
        }
    }
}

/**
 *  Updates the language to french if english selected
 *  and vice versa
 */
fun updateLanguage(context: Context) {
    val configuration = Configuration(context.resources.configuration)
    if (configuration.locales.get(0).toLanguageTag().equals("En", true)) {
        configuration.setLocale(Locale("fr"))
    }
    else {
        configuration.setLocale(Locale("en"))
    }
    context.resources.updateConfiguration(configuration,
        context.resources.displayMetrics)
    (context as MainActivity).recreate()
}
