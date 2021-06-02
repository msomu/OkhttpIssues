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
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.statusBarsHeight
import com.msomu.squareissues.data.Comment
import com.msomu.squareissues.data.GithubIssuesItem
import com.msomu.squareissues.data.IssueData
import com.msomu.squareissues.mock.mockIssues
import com.msomu.squareissues.ui.components.IssueItem
import com.msomu.squareissues.ui.theme.SquareOkhttpIssuesTheme
import com.msomu.squareissues.util.Resource

@Composable
fun IssueDetailScreen(issueId: Int) {
    val viewModel: IssueDetailViewModel = hiltViewModel()
    viewModel.getData(issueId)
    val issue = viewModel.viewIssueState.collectAsState()
    val comments = viewModel.viewCommentsState.collectAsState()
    IssueDetailContent(issue, comments)
}

@Composable
fun IssueDetailContent(
    githubIssuesItem: State<GithubIssuesItem?>,
    commentsResource: State<Resource<List<Comment>>>
) {
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
                    text = "Issue ${githubIssuesItem.value?.number ?: ""}",
                    style = MaterialTheme.typography.h1
                )
            })
        LazyColumn {
            githubIssuesItem.value?.let {
                item {
                    IssueItem(
                        issueData = IssueData(
                            user = it.user,
                            id = it.number,
                            body = it.body,
                            title = it.title,
                            status = it.state,
                            updatedDate = it.updatedAt,
                        ),
                        isDetailPage = true,
                        onClick = { }
                    )
                }
            }
            when (commentsResource.value) {
                is Resource.Loading -> {
                    item {
                        Text(text = "Loading", style = MaterialTheme.typography.h3)
                    }
                }
                is Resource.Error -> {
                    item {
                        Text(
                            text = "Didn't fetch the latest comments",
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
                is Resource.Success -> {
                    item {
                        Text(text = "Comments", style = MaterialTheme.typography.h3)
                    }
                }
            }
            val comments = commentsResource.value.data ?: emptyList()
            items(comments) { item ->
                IssueItem(
                    issueData = IssueData(
                        user = item.user,
                        id = item.id,
                        body = item.body,
                        title = null,
                        status = null,
                        updatedDate = item.updatedAt,
                    ),
                    isDetailPage = true,
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
        IssueDetailScreen(mockIssues()[0].number)
    }
}