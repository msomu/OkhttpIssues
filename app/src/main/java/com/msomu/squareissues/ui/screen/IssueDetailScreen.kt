package com.msomu.squareissues.ui.screen

import android.util.Log
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.statusBarsHeight
import com.msomu.squareissues.data.Comment
import com.msomu.squareissues.data.GithubIssuesItem
import com.msomu.squareissues.mock.mockIssues
import com.msomu.squareissues.ui.components.IssueItem
import com.msomu.squareissues.ui.theme.SquareOkhttpIssuesTheme
const val TAG = "IssueDetailScreen"
@Composable
fun IssueDetailScreen(issueId: Int, navigateBack: () -> Unit) {
    val viewModel : IssueDetailViewModel = hiltViewModel()
    Log.d(TAG, "IssueDetailScreen: number :$issueId")
    viewModel.getData(issueId)
    val issue = viewModel.viewIssueState.value
    val comments = viewModel.viewCommentsState.value.data ?: emptyList()
    if(comments.isEmpty()){
        Log.e(TAG, "IssueDetailScreen: ${viewModel.viewCommentsState.value.error}")
    }
    IssueDetailContent(issue,comments)
}

@Composable
fun IssueDetailContent(githubIssuesItem : GithubIssuesItem?, comments : List<Comment>) {
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
                    text = "Issue ${githubIssuesItem?.number ?: ""}",
                    style = MaterialTheme.typography.h1
                )
            })
        LazyColumn {
            githubIssuesItem?.let {
                item { IssueItem(
                    user = githubIssuesItem.user,
                    isDetailPage = true,
                    id = githubIssuesItem.number,
                    body = githubIssuesItem.body,
                    title = githubIssuesItem.title,
                    status = githubIssuesItem.state,
                    updatedDate = githubIssuesItem.updated_at,
                    onClick = { }
                ) }
            }
            items(comments){item->
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
        IssueDetailScreen(mockIssues()[0].number) {}
    }
}