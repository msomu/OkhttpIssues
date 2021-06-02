package com.msomu.squareissues

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.msomu.squareissues.ui.Actions
import com.msomu.squareissues.ui.Destinations.Home
import com.msomu.squareissues.ui.Destinations.IssueDetail
import com.msomu.squareissues.ui.Destinations.TaskDetailArgs.IssueId
import com.msomu.squareissues.ui.screen.HomeScreen
import com.msomu.squareissues.ui.screen.IssueDetailScreen
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
            val actions = remember(navController) { Actions(navController) }
            NavHost(navController = navController, startDestination = Home) {
                composable(Home) { HomeScreen(actions.openIssue) }
                composable(
                    "$IssueDetail/{$IssueId}",
                    arguments = listOf(navArgument(IssueId) { type = NavType.IntType })
                ) { backStackEntry ->
                    IssueDetailScreen(
                        issueId = backStackEntry.arguments?.getInt(
                            IssueId
                        ) ?: 0,
                        navigateBack = actions.navigateBack
                    )
                }
            }
        }
    }
}
