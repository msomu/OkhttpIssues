package com.msomu.squareissues

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.msomu.squareissues.ui.screen.Home
import com.msomu.squareissues.ui.screen.IssueDetail
import com.msomu.squareissues.ui.theme.SquareOkhttpIssuesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            OkHTTPApp()
        }
    }
}

@Composable
fun OkHTTPApp() {
    SquareOkhttpIssuesTheme {
        ProvideWindowInsets {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable("home") { Home() }
                composable("issue/{issueId}",
                    arguments = listOf(navArgument("issueId") { type = NavType.IntType })
                ) { IssueDetail() }
            }
        }
    }
}
