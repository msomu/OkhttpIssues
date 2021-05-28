package com.msomu.squareissues.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.msomu.squareissues.data.GithubIssuesItem

@Composable
fun Issues(issuesList: List<GithubIssuesItem>, onIssueItemClick: (Int) -> Unit) {
    LazyColumn {
        items(issuesList){item->
            IssueItem(item, onIssueItemClick)
        }
    }
}