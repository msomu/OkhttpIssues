package com.msomu.squareissues.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.insets.statusBarsHeight
import com.msomu.squareissues.data.IssueDetail
import com.msomu.squareissues.mock.mockIssueDetail
import com.msomu.squareissues.mock.mockIssues
import com.msomu.squareissues.ui.components.IssueItem
import com.msomu.squareissues.ui.theme.SquareOkhttpIssuesTheme

@Composable
fun IssueDetailScreen(issueId: Int, navigateBack: () -> Unit) {
    IssueDetailContent(mockIssueDetail())
}

@Composable
fun IssueDetailContent(issueDetail: IssueDetail) {
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
        TopAppBar(
            title = {
                Text(
                    text = "Issue " + issueDetail.githubIssuesItem.number,
                    style = MaterialTheme.typography.h1
                )
            })
        LazyColumn {
            val issue = issueDetail.githubIssuesItem
            item { IssueItem(
                user = issue.user,
                isDetailPage = true,
                id= issue.id,
                body = issue.body,
                title = issue.title,
                status = issue.state,
                updatedDate = issue.updated_at,
                onClick = { }
            ) }
            items(issueDetail.comments){item->
                IssueItem(
                    user = item.user,
                    isDetailPage = true,
                    id= item.id,
                    body = item.body,
                    title = null,
                    status = null,
                    updatedDate = item.updated_at,
                    onClick = { }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultIssueDetailPreview() {
    SquareOkhttpIssuesTheme {
        IssueDetailScreen(mockIssues()[0].id) {}
    }
}