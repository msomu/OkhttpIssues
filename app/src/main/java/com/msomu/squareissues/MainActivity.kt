package com.msomu.squareissues

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.msomu.squareissues.mock.mockIssues
import com.msomu.squareissues.ui.components.IssueItem
import com.msomu.squareissues.ui.screen.Home
import com.msomu.squareissues.ui.theme.SquareOkhttpIssuesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            SquareOkhttpIssuesTheme {
                ProvideWindowInsets {
                    Home()
                }
            }
        }
    }
}