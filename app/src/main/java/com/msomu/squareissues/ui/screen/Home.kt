package com.msomu.squareissues.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.insets.statusBarsHeight
import com.msomu.squareissues.data.GithubIssuesItem
import com.msomu.squareissues.mock.mockIssues
import com.msomu.squareissues.ui.components.HomeAppBar
import com.msomu.squareissues.ui.components.Issues
import com.msomu.squareissues.ui.theme.SquareOkhttpIssuesTheme

@Composable
fun Home() {
    HomeContent(mockIssues())
}

@Composable
fun HomeContent(issuesList : List<GithubIssuesItem>){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val appBarColor = MaterialTheme.colors.surface.copy(alpha = 0.87f)

        // Draw a scrim over the status bar which matches the app bar
        Spacer(
            Modifier
                .background(appBarColor)
                .fillMaxWidth()
                .statusBarsHeight()
        )
        HomeAppBar(MaterialTheme.colors.background)
        Issues(issuesList)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SquareOkhttpIssuesTheme {
        Home()
    }
}